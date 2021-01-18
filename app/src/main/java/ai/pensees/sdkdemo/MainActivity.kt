package ai.pensees.sdkdemo

import ai.pensees.commons.ImageUtils
import ai.pensees.fcompare.PESFCompare
import ai.pensees.fcompare.PESFCompareException
import ai.pensees.sdk.common.SDKConstant
import ai.pensees.sdk.common.data.FaceInfo
import ai.pensees.sdk.facedetect.PESFaceDetect
import ai.pensees.sdk.facefeature.PESFeature
import ai.pensees.sdk.facequality.PESQuality
import ai.pensees.sdk.maskdetect.PESMask
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    private var pesfCompare: PESFCompare? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initSDK()
    }

    private fun initSDK(){
        var ret = PESFaceDetect.init(this)
        Log.e("zzz","ret detect = $ret")
        ret = PESQuality.init(this)
        Log.e("zzz","ret quality = $ret")
        ret = PESMask.init(this)
        Log.e("zzz","ret2 mask = $ret")
        ret = PESFeature.init(this, PESFeature.Version.Mask)
        Log.e("zzz","ret3 feature = $ret")
    }

    private fun releaseSDK()
    {
        PESFaceDetect.release()
        PESFeature.release()
        PESMask.release()
        PESQuality.release()
    }
    fun initPESCompare() {
        releasePESCompare()
        pesfCompare = PESFCompare()
        pesfCompare!!.threshold = 0.65f
        pesfCompare!!.maxResult = 3
        PESFCompare.setMpThreads(4)
        try {
            //只支持sqlite db 存储特征值。特征使用blob形式存储。字段可以任意指定。sqlite 数据库需要业务自己管理。
            pesfCompare?.init("/sdcard/localthface.db", "FACE_FEATURE", "FEATURE_ID", "FEATURE")
            pesfCompare?.loadDB()
        } catch (e: PESFCompareException) {
            e.printStackTrace()
        }
    }

    private fun releasePESCompare() {
        if (pesfCompare != null) {
            pesfCompare?.release()
            pesfCompare = null
        }
    }

    override fun onResume() {
        super.onResume()
        Thread{
            checkFace(R.drawable.gtw2)
            start(R.drawable.gtw2)
        }.start()
    }

    fun compare()
    {
        val result = pesfCompare!!.compare(getFromRaw(R.raw.slh))
    }

    fun getFromRaw(resid: Int): ByteArray? {
        return try {
            val instream = resources.openRawResource(resid)
            //获取文件的字节数
            val lenght = instream.available()
            //创建byte数组
            val buffer = ByteArray(lenght)
            //将文件中的数据读到byte数组中
            instream.read(buffer)
            buffer
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //检测人脸个数
    fun checkFace(resId:Int) {
        val bitmap = BitmapFactory.decodeResource(getResources(), resId)
        val bgr = ImageUtils.bitmapToBGR(bitmap)

        for (i in 0..10) {
            val start = System.currentTimeMillis()
            var count = PESFaceDetect.check(
                    bgr,
                    bitmap.width,
                    bitmap.height,
                    SDKConstant.IMAGE_FORMAT_BGR
            )
            val end = System.currentTimeMillis()
            Log.e("zzz", "check count = $count,time = ${end -start}")
        }
    }

    fun start(resId: Int) {
        val bitmap = BitmapFactory.decodeResource(getResources(), resId)
        val bgr = ImageUtils.bitmapToBGR(bitmap)

        for (i in 0..10) {
            val start = System.currentTimeMillis();

            var result:List<FaceInfo> = PESFaceDetect.detect(
                    bgr,
                    bitmap.width,
                    bitmap.height,
                    SDKConstant.IMAGE_FORMAT_BGR
            )

            val end = System.currentTimeMillis()
            val qualuty = PESQuality.detect(result.first().rect,bgr,bitmap.width, bitmap.height, SDKConstant.IMAGE_FORMAT_BGR)

            val end2 = System.currentTimeMillis()
            val mask = PESMask.detect(result.first().landmark.raw,bgr,bitmap.width,bitmap.height,SDKConstant.IMAGE_FORMAT_BGR)


            val end3 = System.currentTimeMillis()
            val feature = PESFeature.extract(result.first().landmark.raw,bgr,bitmap.width,bitmap.height,SDKConstant.IMAGE_FORMAT_BGR)

            val end4 = System.currentTimeMillis()
            Log.e("zzz", "count = ${result.size} time:${end -start}\n quality = $qualuty time:${end2 -end} \n mask=$mask time:${end3 - end2}\n feature = ${feature.size} time:${end4 - end3}")
        }
    }

    override fun onDestroy() {
        releaseSDK()
        releasePESCompare()
        super.onDestroy()
    }
}