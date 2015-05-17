package com.ttxr.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.ttxr.bean.UserBean;

public class DBHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "DBHelper";
    private static final String DATABASE_NAME = "XL.db";
    private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @param context
     * @param databaseName
     * @param factory
     * @param databaseVersion
     */
    public DBHelper(Context context, String databaseName,
                    CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, UserBean.class, false);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        super.close();
    }


    public static <D extends RuntimeExceptionDao<T, ?>, T> D getDao_(Context context, Class<T> clazz) {
        return OpenHelperManager.getHelper(context, DBHelper.class).getRuntimeExceptionDao(clazz);
    }

}
