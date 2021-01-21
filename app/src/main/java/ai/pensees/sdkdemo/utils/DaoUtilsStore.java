package ai.pensees.sdkdemo.utils;

//import ai.pensees.sdkdemo.dao.UserDao;
import ai.pensees.sdkdemo.model.UserModel;

/**
 * 初始化、存放及获取DaoUtils
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private CommonDaoUtils<UserModel> mUserDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager mManager = DaoManager.getInstance();
//        UserDao _UserDao = mManager.getDaoSession().getUserDao();
//        mUserDaoUtils = new CommonDaoUtils<>(UserModel.class, _UserDao);
    }

    public CommonDaoUtils<UserModel> getUserDaoUtils() {
        return mUserDaoUtils;
    }

}
