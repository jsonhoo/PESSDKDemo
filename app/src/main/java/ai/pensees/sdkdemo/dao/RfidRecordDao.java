package ai.pensees.sdkdemo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import ai.pensees.sdkdemo.model.FaceRecord;

public class RfidRecordDao extends AbstractDao<FaceRecord,Integer> {

    public static final String TABLENAME = "FACE_RECORD";

    public RfidRecordDao(DaoConfig config) {
        super(config);
    }

    @Override
    protected FaceRecord readEntity(Cursor cursor, int offset) {
        return null;
    }

    @Override
    protected Integer readKey(Cursor cursor, int offset) {
        return null;
    }

    @Override
    protected void readEntity(Cursor cursor, FaceRecord entity, int offset) {

    }

    @Override
    protected void bindValues(DatabaseStatement stmt, FaceRecord entity) {

    }

    @Override
    protected void bindValues(SQLiteStatement stmt, FaceRecord entity) {

    }

    @Override
    protected Integer updateKeyAfterInsert(FaceRecord entity, long rowId) {
        return null;
    }

    @Override
    protected Integer getKey(FaceRecord entity) {
        return null;
    }

    @Override
    protected boolean hasKey(FaceRecord entity) {
        return false;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
