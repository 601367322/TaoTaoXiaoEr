package com.ttxr.share;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalSqLiteHelper extends SQLiteOpenHelper {
    public LocalSqLiteHelper(Context context, String name,
                             SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "
                + SharedData.TABLE_NAME + "(" + SharedData.ID
                + " integer primary key autoincrement,"
                + SharedData.KEY + " varchar,"
                + SharedData.M_STR + " varchar,"
                + SharedData.M_BOOLEAN + " integer(1),"
                + SharedData.M_DATE + " datetime,"
                + SharedData.M_INT + " integer,"
                + SharedData.M_LONG + " int8, "
                + SharedData.M_FLOAT + " float,"
                + SharedData.DATA_TYPE + " integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion,
                          int newVersion) {
        onCreate(sqLiteDatabase);
    }
}
