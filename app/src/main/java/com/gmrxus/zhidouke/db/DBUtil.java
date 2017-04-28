package com.gmrxus.zhidouke.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mac on 2017/4/25.
 */

public class DBUtil extends SQLiteOpenHelper {
    public static final String DB_NAME = "zhidouke.db";
    public static final int DB_VERSION = 1;
    private final Context mContext;

    private String createZhiHuSql = "create table Zhihu(" +
            "id integer primary key autoincrement," +
            "content text," +
            "date text unique," +//唯一字段
            "getTime text" +
            ")";

    public DBUtil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.mContext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createZhiHuSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
