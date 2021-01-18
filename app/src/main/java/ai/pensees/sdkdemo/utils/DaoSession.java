package ai.pensees.sdkdemo.utils;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import ai.pensees.sdkdemo.dao.UserDao;
import ai.pensees.sdkdemo.model.UserModel;


public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;

    private final UserDao userDao;


    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);
        userDao = new UserDao(userDaoConfig, this);
        registerDao(UserModel.class, userDao);
    }

    public void clear() {
        userDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

}