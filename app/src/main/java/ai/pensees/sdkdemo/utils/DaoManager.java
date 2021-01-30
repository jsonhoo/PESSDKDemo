package ai.pensees.sdkdemo.utils;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.IOException;

import ai.pensees.commons.BuildConfig;
import ai.pensees.sdkdemo.gen.DaoMaster;
import ai.pensees.sdkdemo.gen.DaoSession;

public class DaoManager {

    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "UserInfo.db";

    private Application mApplication;

    //多线程中要被共享的使用volatile关键字修饰
    private volatile static DaoManager manager = new DaoManager();
    private DaoMaster mDaoMaster;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoSession mDaoSession;
    private Context mContext;

    /**
     * 单例模式获得操作数据库对象
     */
    public static DaoManager getInstance() {
        return manager;
    }

    private DaoManager() {
        setDebug();
    }

    public void init(Application application) {
        this.mApplication = application;
        getDaoMaster();
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            mContext = new GreenDaoContext(mApplication);
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mApplication, DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug() {
        if (BuildConfig.DEBUG) {
            QueryBuilder.LOG_SQL = true;
            QueryBuilder.LOG_VALUES = true;
        }
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public File getDbPath() {
        return mApplication.getDatabasePath(DB_NAME);
    }

    public static class GreenDaoContext extends ContextWrapper {

        private String currentUserId = "greendao";//一般用来针对一个用户一个数据库，以免数据混乱问题
        private Context mContext;

        public GreenDaoContext(Context context) {
            super(context);
            this.mContext = context;
        }

        /**
         * 获得数据库路径，如果不存在，则创建对象
         *
         * @param dbName
         */
        @Override
        public File getDatabasePath(String dbName) {
            String dbDir = Environment.getExternalStorageDirectory().getPath();
            if (TextUtils.isEmpty(dbDir)) {
                Log.e("SD卡管理：", "SD卡不存在，请加载SD卡");
                return null;
            }
            File baseFile = new File(dbDir);
            // 目录不存在则自动创建目录
            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
            StringBuffer buffer = new StringBuffer();
            buffer.append(baseFile.getPath());
            buffer.append(File.separator);
            buffer.append(currentUserId);
            dbDir = buffer.toString();// 数据库所在目录
            buffer.append(File.separator);
            buffer.append(dbName);
            String dbPath = buffer.toString();// 数据库路径
            // 判断目录是否存在，不存在则创建该目录
            File dirFile = new File(dbDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            // 数据库文件是否创建成功
            boolean isFileCreateSuccess = false;
            // 判断文件是否存在，不存在则创建该文件
            File dbFile = new File(dbPath);
            if (!dbFile.exists()) {
                try {
                    isFileCreateSuccess = dbFile.createNewFile();// 创建文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else
                isFileCreateSuccess = true;
            // 返回数据库文件对象
            if (isFileCreateSuccess)
                return dbFile;
            else
                return super.getDatabasePath(dbName);
        }

        /**
         * 重载这个方法，是用来打开SD卡上的数据库的，android 2.3及以下会调用这个方法。
         *
         * @param name
         * @param mode
         * @param factory
         */
        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
            SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
            return result;
        }

        /**
         * Android 4.0会调用此方法获取数据库。
         *
         * @param name
         * @param mode
         * @param factory
         * @param errorHandler
         * @see android.content.ContextWrapper#openOrCreateDatabase(java.lang.String, int,
         * android.database.sqlite.SQLiteDatabase.CursorFactory,
         * android.database.DatabaseErrorHandler)
         */
        @Override
        public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
            SQLiteDatabase result = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);

            return result;
        }

    }

}
