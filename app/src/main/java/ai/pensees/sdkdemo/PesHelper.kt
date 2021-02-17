package ai.pensees.sdkdemo

import ai.pensees.fcompare.PESFCompare
import ai.pensees.fcompare.PESFCompareException
import ai.pensees.sdk.facedetect.PESFaceDetect
import ai.pensees.sdk.facefeature.PESFeature
import ai.pensees.sdk.facequality.PESQuality
import ai.pensees.sdk.maskdetect.PESMask
import ai.pensees.sdkdemo.gen.UserModelDao
import ai.pensees.sdkdemo.utils.DaoManager
import android.content.Context
import android.util.Log

/**
 * @author liujiansheng
 * @since 2021/1/30
 */
object PesHelper {
    const val TAG = "liu.js"
    var pesfCompare: PESFCompare? = null
    var mCallback: Callback? = null
    private var mHasInit = false

    @Synchronized
    public fun init(context: Context) {
        if (mHasInit) {
            return
        }
        Thread {
            initImpl(context)
            mCallback?.onInitSuccess()
            mHasInit = true
        }.start()
    }

    private fun initImpl(context: Context) {
        var ret = PESFaceDetect.init(context)
        Log.d(TAG, "ret detect = $ret")
        ret = PESQuality.init(context)
        Log.d(TAG, "ret quality = $ret")
        ret = PESMask.init(context)
        Log.d(TAG, "ret2 mask = $ret")
        ret = PESFeature.init(context, PESFeature.Version.Mask)
        Log.d(TAG, "ret3 feature = $ret")
        initPESCompare()
    }

    private fun initPESCompare() {
        releasePESCompare()
        pesfCompare = PESFCompare()
        pesfCompare!!.threshold = 0.65f
        pesfCompare!!.maxResult = 3
        PESFCompare.setMpThreads(4)
        try {
            val dbpath = DaoManager.getInstance().dbPath.absolutePath
            val tablename = UserModelDao.TABLENAME
            val featureIdColName = UserModelDao.Properties.FaceUrl.columnName
            val featureColName = UserModelDao.Properties.Feature.columnName
            Log.d(HomeActivity.TAG, "init compare--dbpath=$dbpath,tableName=$tablename,idColumn=$featureIdColName,featureColumn=$featureColName")
            pesfCompare?.init(dbpath, tablename, featureIdColName, featureColName)
            pesfCompare?.loadDB()
        } catch (e: PESFCompareException) {
            Log.e(TAG, "", e)
        }
    }

    private fun releasePESCompare() {
        if (pesfCompare != null) {
            pesfCompare?.release()
            pesfCompare = null
        }
    }

    fun releaseSDK() {
        mHasInit = false
        PESFaceDetect.release()
        PESFeature.release()
        PESMask.release()
        PESQuality.release()
    }

    interface Callback {
        fun onInitSuccess()
    }
}