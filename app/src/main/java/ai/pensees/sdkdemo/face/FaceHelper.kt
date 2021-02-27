package ai.pensees.sdkdemo.face

import ai.pensees.commons.ImageUtils
import ai.pensees.fcompare.PESFCompareException
import ai.pensees.sdk.common.SDKConstant
import ai.pensees.sdk.facedetect.PESFaceDetect
import ai.pensees.sdk.facefeature.PESFeature
import ai.pensees.sdkdemo.HomeActivity
import ai.pensees.sdkdemo.PesHelper.TAG
import ai.pensees.sdkdemo.PesHelper.pesfCompare
import ai.pensees.sdkdemo.PessApplication
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.blankj.utilcode.util.CollectionUtils
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
    private var mTimer: Timer = Timer()
    private var mDoorIsOpen = false
    private var mIsInit = false

    fun init(cameraView: CameraView) {
        if (mIsInit) {
            return
        }
        initThread()
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

        }, 3000, 1200)
    }

    private fun initThread() {
        mHandlerThread = HandlerThread("take-picture")
        mHandlerThread?.start()
        mHandler = Handler(mHandlerThread!!.looper)
    }

    fun takePictureAndCompare() {
        mHandler?.removeCallbacks(mTakePictureRunnable)
        mHandler?.post(mTakePictureRunnable)
    }

    fun extractFeature(uri: Uri, callback: ExtractCallback) {
        mHandler?.post(ExtractFeatureRunnable(uri, callback))
    }

    private val mTakePictureRunnable = Runnable {
        mCameraView?.takePictureSnapshot()
    }

    private var mCameraListener = object : CameraListener() {
        override fun onPictureTaken(result: PictureResult) {
            super.onPictureTaken(result)
            Log.d(HomeActivity.TAG, "onPictureTaken--")
            if (!mIsInit) {
                return
            }
            mHandler?.removeCallbacks(mCompareRunnable)
            mCompareRunnable.mResult = result
            mHandler?.post(mCompareRunnable)
        }
    }

    fun reloadCompareDB() {
        try {
            pesfCompare!!.reloadDB()
        } catch (e: PESFCompareException) {
            Log.e(HomeActivity.TAG, "", e)
        }
    }

    private var mCompareRunnable: CompareRunnable = CompareRunnable()

    private class CompareRunnable : Runnable {
        var mResult: PictureResult? = null

        override fun run() {
            mResult?.toBitmap { bitmap: Bitmap? ->
                try {
                    if (!mIsInit) {
                        return@toBitmap
                    }
                    Log.d(HomeActivity.TAG, "onPictureTaken-toBitmap----")
                    val size: Size = mResult!!.size
                    val toRGB = ImageUtils.bitmapToRGB(bitmap)
                    Log.d(HomeActivity.TAG, "onPictureTaken-toRGB----")
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
                        Log.d(HomeActivity.TAG, "onPictureTaken-compare--fcResults Empty")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "", e)
                }

            }
        }
    }

    private class ExtractFeatureRunnable(private var mUri: Uri?, private var mCallback: ExtractCallback?) : Runnable {

        override fun run() {
            val bitmap = BitmapFactory.decodeFile(mUri?.path)
            val toRGB = ImageUtils.bitmapToRGB(bitmap)
            val faceInfos = PESFaceDetect.detect(toRGB, bitmap.width, bitmap.height, SDKConstant.IMAGE_FORMAT_RGB)
            Log.d(HomeActivity.TAG, "ExtractFeature--Faces Count=" + CollectionUtils.size(faceInfos))
            if (CollectionUtils.size(faceInfos) == 0) {
                mCallback?.onExtractError()
                return
            }
            val featureBytes = PESFeature.extract(toRGB, faceInfos[0].landmark, bitmap.width, bitmap.height, SDKConstant.IMAGE_FORMAT_RGB)
            Log.d(HomeActivity.TAG, "ExtractFeature--featureBytes$featureBytes")
            mCallback?.onExtractSuccess(featureBytes)
        }

    }

    private fun openDoor() {
        if (mDoorIsOpen) {
            return
        }
        Log.d(TAG, "open Door")
        IflytekHelper.speaking(PessApplication.getApplication(), "识别成功，已开门")
        mDoorIsOpen = true
        mHandler?.postDelayed({
            mDoorIsOpen = false
            IflytekHelper.speaking(PessApplication.getApplication(), "已关门")
        }, 10000)
    }

    fun release() {
        mIsInit = false;
        mTimer.cancel()
        mHandler?.removeCallbacks(mCompareRunnable)
        mHandler?.removeCallbacks(mTakePictureRunnable)
        mCameraView?.removeCameraListener(mCameraListener)
        mHandlerThread?.quitSafely()
        mHandlerThread = null
        mHandler = null
    }

    interface ExtractCallback {
        fun onExtractError();
        fun onExtractSuccess(featureBytes: ByteArray);
    }
}