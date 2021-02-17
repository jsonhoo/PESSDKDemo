package ai.pensees.sdkdemo.face

import ai.pensees.commons.ImageUtils
import ai.pensees.fcompare.PESFCompareException
import ai.pensees.sdk.common.SDKConstant
import ai.pensees.sdk.facedetect.PESFaceDetect
import ai.pensees.sdk.facefeature.PESFeature
import ai.pensees.sdkdemo.HomeActivity
import ai.pensees.sdkdemo.PesHelper.TAG
import ai.pensees.sdkdemo.PesHelper.pesfCompare
import ai.pensees.sdkdemo.model.UserModel
import ai.pensees.sdkdemo.utils.DaoManager
import android.graphics.Bitmap
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.blankj.utilcode.util.CollectionUtils
import com.otaliastudios.cameraview.BitmapCallback
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.CameraView
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.size.Size
import java.util.*

/**
 * @author liujiansheng
 * @since 2021/2/17
 */
object FaceHelper {
    private var mHandlerThread: HandlerThread? = null
    private var mHandler: Handler? = null
    private var mCameraView: CameraView? = null;
    private const val ACTION_COMPARE = "compare"
    private const val ACTION_EXTRACT = "EXTRACT_FEATURE"
    private var mAction: String = ACTION_COMPARE
    private var mTimer: Timer = Timer()
    private var mDoorIsOpen = false
    private var mIsInit = false

    fun init(cameraView: CameraView) {
        if (mIsInit) {
            return
        }
        mIsInit = true
        mCameraView = cameraView
        mCameraView!!.addCameraListener(mCameraListener)
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                Log.d(HomeActivity.TAG, "compare timer start--mDoorIsOpen=$mDoorIsOpen")
                if (!mDoorIsOpen) {
                    takePictureAndCompare()
                }
            }

        }, 3000, 2000)
    }

    init {
        mHandlerThread = HandlerThread("take-picture")
        mHandlerThread?.start()
        mHandler = Handler(mHandlerThread!!.looper)
    }

    fun extractFeature() {
        mAction = ACTION_EXTRACT
        mHandler?.post(mTakePictureRunnable)
    }

    fun takePictureAndCompare() {
        mAction = ACTION_COMPARE
        mHandler?.removeCallbacks(mTakePictureRunnable)
        mHandler?.post(mTakePictureRunnable)
    }

    private val mTakePictureRunnable = Runnable {
        mCameraView?.takePictureSnapshot()
    }

    private var sUserNo = 0
    private fun createUserModel(): UserModel {
        val userModel = UserModel()
        userModel.carNo = "1111111"
        userModel.createTime = System.currentTimeMillis()
        userModel.updateTime = System.currentTimeMillis()
        userModel.userNo = "" + sUserNo
        userModel.userName = "test1"
        userModel.faceUrl = "faceUrl"
        sUserNo++
        return userModel
    }

    private var mCameraListener = object : CameraListener() {
        override fun onPictureTaken(result: PictureResult) {
            super.onPictureTaken(result)
            Log.d(HomeActivity.TAG, "onPictureTaken--Action=$mAction--")
            if (ACTION_EXTRACT === mAction) {
                result.toBitmap(BitmapCallback { bitmap ->
                    Log.d(HomeActivity.TAG, "onPictureTaken-toRGB--Action$mAction--")
                    //                    int count = PESFaceDetect.check(toRGB, size.getWidth(), size.getHeight(), SDKConstant.IMAGE_FORMAT_RGB);
                    val size = result.size
                    val toRGB = ImageUtils.bitmapToRGB(bitmap)
                    val faceInfos = PESFaceDetect.detect(toRGB, size.width, size.height, SDKConstant.IMAGE_FORMAT_RGB)
                    Log.d(HomeActivity.TAG, "onPictureTaken-detect--Action$mAction--")
                    Log.d(HomeActivity.TAG, "Faces Count=" + CollectionUtils.size(faceInfos))
                    if (CollectionUtils.size(faceInfos) == 0) {
                        return@BitmapCallback
                    }
                    val featureBytes = PESFeature.extract(toRGB, faceInfos[0].landmark, size.width, size.height, SDKConstant.IMAGE_FORMAT_RGB)
                    Log.d(HomeActivity.TAG, "onPictureTaken-extract--Action$mAction")
                    val userModel: UserModel = createUserModel()
                    userModel.feature = featureBytes
                    Log.d(HomeActivity.TAG, "人脸数据录入成功--")
                    DaoManager.getInstance().daoSession.userModelDao.insertOrReplace(userModel)
                    try {
                        pesfCompare!!.reloadDB()
                    } catch (e: PESFCompareException) {
                        Log.e(HomeActivity.TAG, "", e)
                    }
                    mAction = ACTION_COMPARE
                })
            } else {
                mHandler?.removeCallbacks(mCompareRunnable)
                mCompareRunnable.mResult = result
                mHandler?.post(mCompareRunnable)
            }
        }
    }

    private var mCompareRunnable: CompareRunnable = CompareRunnable()

    private class CompareRunnable : Runnable {
        var mResult: PictureResult? = null

        override fun run() {
            mResult?.toBitmap { bitmap: Bitmap? ->
                Log.d(HomeActivity.TAG, "onPictureTaken-toBitmap----")
                val size: Size = mResult!!.size
                val toRGB = ImageUtils.bitmapToRGB(bitmap)
                Log.d(HomeActivity.TAG, "onPictureTaken-toRGB----")
                //                    int count = PESFaceDetect.check(toRGB, size.getWidth(), size.getHeight(), SDKConstant.IMAGE_FORMAT_RGB);
                val faceInfoList = PESFaceDetect.detect(toRGB, size.width, size.height, SDKConstant.IMAGE_FORMAT_RGB)
                Log.d(HomeActivity.TAG, "onPictureTaken-detect----")
                Log.d(HomeActivity.TAG, "Faces Count=" + CollectionUtils.size(faceInfoList))
                if (CollectionUtils.size(faceInfoList) == 0) {
                    return@toBitmap
                }
                val featureBytes = PESFeature.extract(toRGB, faceInfoList[0].landmark, size.width, size.height, SDKConstant.IMAGE_FORMAT_RGB)
                Log.d(HomeActivity.TAG, "onPictureTaken-featureBytes----")
                val fcResults = pesfCompare!!.compare(featureBytes)
                if (!CollectionUtils.isEmpty(fcResults)) {
                    val hasCompare = (fcResults[0].score > 0.7f)
                    Log.d(HomeActivity.TAG, "onPictureTaken-compare--hasCompare=" + hasCompare + "--score=" + fcResults[0].score)
                    if (hasCompare) {
                        openDoor()
                    }
                } else {
                    Log.d(HomeActivity.TAG, "onPictureTaken-compare--hasCompare=" + false)
                }
            }
        }
    }

    private fun openDoor() {
        if (mDoorIsOpen) {
            return
        }
        Log.d(TAG, "open Door")
        mDoorIsOpen = true
        mHandler?.postDelayed({
            mDoorIsOpen = false
        }, 10000)
    }
}