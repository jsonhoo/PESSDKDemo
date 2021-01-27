package ai.pensees.sdkdemo.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import ai.pensees.sdkdemo.model.UserModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_MODEL".
*/
public class UserModelDao extends AbstractDao<UserModel, Long> {

    public static final String TABLENAME = "USER_MODEL";

    /**
     * Properties of entity UserModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property UserNo = new Property(1, String.class, "userNo", false, "USER_NO");
        public final static Property UserName = new Property(2, String.class, "userName", false, "USER_NAME");
        public final static Property Feature = new Property(3, byte[].class, "feature", false, "FEATURE");
        public final static Property FaceUrl = new Property(4, String.class, "faceUrl", false, "FACE_URL");
        public final static Property CreateTime = new Property(5, long.class, "createTime", false, "CREATE_TIME");
        public final static Property UpdateTime = new Property(6, long.class, "updateTime", false, "UPDATE_TIME");
        public final static Property CarNo = new Property(7, String.class, "carNo", false, "CAR_NO");
    }


    public UserModelDao(DaoConfig config) {
        super(config);
    }
    
    public UserModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_MODEL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"USER_NO\" TEXT UNIQUE ," + // 1: userNo
                "\"USER_NAME\" TEXT NOT NULL ," + // 2: userName
                "\"FEATURE\" BLOB NOT NULL ," + // 3: feature
                "\"FACE_URL\" TEXT NOT NULL ," + // 4: faceUrl
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 5: createTime
                "\"UPDATE_TIME\" INTEGER NOT NULL ," + // 6: updateTime
                "\"CAR_NO\" TEXT);"); // 7: carNo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_MODEL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String userNo = entity.getUserNo();
        if (userNo != null) {
            stmt.bindString(2, userNo);
        }
        stmt.bindString(3, entity.getUserName());
        stmt.bindBlob(4, entity.getFeature());
        stmt.bindString(5, entity.getFaceUrl());
        stmt.bindLong(6, entity.getCreateTime());
        stmt.bindLong(7, entity.getUpdateTime());
 
        String carNo = entity.getCarNo();
        if (carNo != null) {
            stmt.bindString(8, carNo);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserModel entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String userNo = entity.getUserNo();
        if (userNo != null) {
            stmt.bindString(2, userNo);
        }
        stmt.bindString(3, entity.getUserName());
        stmt.bindBlob(4, entity.getFeature());
        stmt.bindString(5, entity.getFaceUrl());
        stmt.bindLong(6, entity.getCreateTime());
        stmt.bindLong(7, entity.getUpdateTime());
 
        String carNo = entity.getCarNo();
        if (carNo != null) {
            stmt.bindString(8, carNo);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UserModel readEntity(Cursor cursor, int offset) {
        UserModel entity = new UserModel( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userNo
            cursor.getString(offset + 2), // userName
            cursor.getBlob(offset + 3), // feature
            cursor.getString(offset + 4), // faceUrl
            cursor.getLong(offset + 5), // createTime
            cursor.getLong(offset + 6), // updateTime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7) // carNo
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserModel entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUserNo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserName(cursor.getString(offset + 2));
        entity.setFeature(cursor.getBlob(offset + 3));
        entity.setFaceUrl(cursor.getString(offset + 4));
        entity.setCreateTime(cursor.getLong(offset + 5));
        entity.setUpdateTime(cursor.getLong(offset + 6));
        entity.setCarNo(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserModel entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserModel entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserModel entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}