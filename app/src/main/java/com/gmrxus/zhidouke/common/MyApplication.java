package com.gmrxus.zhidouke.common;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gmrxus.zhidouke.db.DBUtil;

/**
 * Created by mac on 2017/4/26.
 */

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    public static Context context;
    public static DBUtil dbUtil;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        dbUtil = new DBUtil(context);
        SQLiteDatabase db = dbUtil.getWritableDatabase();

    }


}
