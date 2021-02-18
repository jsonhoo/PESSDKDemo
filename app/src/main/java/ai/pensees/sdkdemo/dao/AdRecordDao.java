package ai.pensees.sdkdemo.dao;


import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import ai.pensees.sdkdemo.model.AdRecord;

public class AdRecordDao extends AbstractDao<AdRecord,Integer> {

    public static final String TABLENAME = "AD_RECORD";

    public AdRecordDao(DaoConfig config) {
        super(config);
    }

    @Override
    protected AdRecord readEntity(Cursor cursor, int offset) {
        return null;
    }

    @Override
    protected Integer readKey(Cursor cursor, int offset) {
        return null;
    }

    @Override
    protected void readEntity(Cursor cursor, AdRecord entity, int offset) {

    }

    @Override
    protected void bindValues(DatabaseStatement stmt, AdRecord entity) {

    }

    @Override
    protected void bindValues(SQLiteStatement stmt, AdRecord entity) {

    }

    @Override
    protected Integer updateKeyAfterInsert(AdRecord entity, long rowId) {
        return null;
    }

    @Override
    protected Integer getKey(AdRecord entity) {
        return null;
    }

    @Override
    protected boolean hasKey(AdRecord entity) {
        return false;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }


}
