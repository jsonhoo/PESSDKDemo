package ai.pensees.sdkdemo.dao;//package ai.pensees.sdkdemo.dao;
//
//import android.database.Cursor;
//import android.database.sqlite.SQLiteStatement;
//
//import org.greenrobot.greendao.AbstractDao;
//import org.greenrobot.greendao.Property;
//import org.greenrobot.greendao.internal.DaoConfig;
//import org.greenrobot.greendao.database.Database;
//import org.greenrobot.greendao.database.DatabaseStatement;
//
//import ai.pensees.sdkdemo.model.UserModel;
//import ai.pensees.sdkdemo.utils.DaoSession;
//
//public class UserDao extends AbstractDao<UserModel, Long> {
//
//    public static final String TABLENAME = "USER_MODEL";
//
//    /**
//     * Properties of entity User.<br/>
//     * Can be used for QueryBuilder and for referencing column names.
//     */
//    public static class Properties {
//        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
//        public final static Property UserId = new Property(1, String.class, "userId", false, "USER_ID");
//        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
//        public final static Property Age = new Property(3, int.class, "age", false, "AGE");
//    }
//
//
//    public UserDao(DaoConfig config) {
//        super(config);
//    }
//
//    public UserDao(DaoConfig config, DaoSession daoSession) {
//        super(config, daoSession);
//    }
//
//    /** Creates the underlying database table. */
//    public static void createTable(Database db, boolean ifNotExists) {
//        String constraint = ifNotExists? "IF NOT EXISTS ": "";
//        db.execSQL("CREATE TABLE " + constraint + "\"USER_MODEL\" (" + //
//                "\"id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
//                "\"USER_ID\" TEXT UNIQUE ," + // 1: userId
//                "\"USER_NAME\" TEXT," + // 2: userName
//                "\"AGE\" INTEGER NOT NULL );"); // 3: age
//    }
//
//    /** Drops the underlying database table. */
//    public static void dropTable(Database db, boolean ifExists) {
//        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
//        db.execSQL(sql);
//    }
//
//    @Override
//    protected final void bindValues(DatabaseStatement stmt, UserModel entity) {
//        stmt.clearBindings();
//
//        Long id = entity.getId();
//        if (id != null) {
//            stmt.bindLong(1, id);
//        }
//
//        String userId = entity.getUserId();
//        if (userId != null) {
//            stmt.bindString(2, userId);
//        }
//
//        String userName = entity.getUserName();
//        if (userName != null) {
//            stmt.bindString(3, userName);
//        }
//        stmt.bindLong(4, entity.getAge());
//    }
//
//    @Override
//    protected final void bindValues(SQLiteStatement stmt, UserModel entity) {
//        stmt.clearBindings();
//
//        Long id = entity.getId();
//        if (id != null) {
//            stmt.bindLong(1, id);
//        }
//
//        String userId = entity.getUserId();
//        if (userId != null) {
//            stmt.bindString(2, userId);
//        }
//
//        String userName = entity.getUserName();
//        if (userName != null) {
//            stmt.bindString(3, userName);
//        }
//        stmt.bindLong(4, entity.getAge());
//    }
//
//    @Override
//    public Long readKey(Cursor cursor, int offset) {
//        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
//    }
//
//    @Override
//    public UserModel readEntity(Cursor cursor, int offset) {
//        UserModel entity = new UserModel( //
//                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
//                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userId
//                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userName
//                cursor.getInt(offset + 3) // age
//        );
//        return entity;
//    }
//
//    @Override
//    public void readEntity(Cursor cursor, UserModel entity, int offset) {
//        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
//        entity.setUserId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
//        entity.setUserName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
//        entity.setAge(cursor.getInt(offset + 3));
//    }
//
//    @Override
//    protected final Long updateKeyAfterInsert(UserModel entity, long rowId) {
//        entity.setId(rowId);
//        return rowId;
//    }
//
//    @Override
//    public Long getKey(UserModel entity) {
//        if(entity != null) {
//            return entity.getId();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public boolean hasKey(UserModel entity) {
//        return entity.getId() != null;
//    }
//
//    @Override
//    protected final boolean isEntityUpdateable() {
//        return true;
//    }
//
//}