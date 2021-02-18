package ai.pensees.sdkdemo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.StrictMode;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.tencent.rtmp.TXLiveBase;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ai.pensees.sdkdemo.gen.DaoMaster;
import ai.pensees.sdkdemo.gen.DaoSession;
import ai.pensees.sdkdemo.utils.DaoManager;
import androidx.multidex.MultiDexApplication;

public class PessApplication extends MultiDexApplication {
    private static String TAG = "PessApplication";

    private static PessApplication instance;
    private DaoSession daoSession;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DaoManager.getInstance().init(this);
        TXLiveBase.setConsoleEnabled(true);

        // 短视频licence设置
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }
        initDreenDao();
        closeAndroidPDialog();
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

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void initDreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "/sdcard/localthface.db");
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}