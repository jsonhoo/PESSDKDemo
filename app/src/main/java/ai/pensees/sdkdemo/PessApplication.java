package ai.pensees.sdkdemo;

import android.os.Build;
import android.os.StrictMode;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.rtmp.TXLiveBase;

import ai.pensees.sdkdemo.utils.DaoManager;
import androidx.multidex.MultiDexApplication;

public class PessApplication extends MultiDexApplication {
    private static String TAG = "PessApplication";

    private static PessApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaoManager.getInstance().init(this);
        TXLiveBase.setConsoleEnabled(true);
        Fresco.initialize(this);
        // 短视频licence设置
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=602cfe5a");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        PesHelper.INSTANCE.releaseSDK();
    }

    public static PessApplication getApplication() {
        return instance;
    }
}