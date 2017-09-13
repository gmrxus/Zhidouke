package com.gmrxus.zhidouke.douban;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmrxus.zhidouke.bean.Douban;
import com.gmrxus.zhidouke.common.MyApplication;
import com.gmrxus.zhidouke.common.Urls;
import com.gmrxus.zhidouke.util.DateUtil;
import com.gmrxus.zhidouke.util.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by mac on 2017/5/2.
 */

public class DoubanModel implements DoubanContract.Model {
    private static final String TAG = "DoubanModel";

    private final Context mContext;
    private final Gson mGson;
    private final SQLiteDatabase mDb;

    public DoubanModel(Context context) {
        this.mContext = context;
        this.mGson = new Gson();
        this.mDb = MyApplication.dbUtil.getWritableDatabase();

    }


    @Override
    public void getForNet(long date, final ResultListener listener) {
        HttpUtils.getY(Urls.DOUBAN_MAIN_URL + DateUtil.doubanDate(date), new HttpUtils.OnHttpRequestCallBackListener() {
            @Override
            public void onFailure(IOException e) {
                listener.onError();
            }

            @Override
            public void onResponse(String response) {
                Douban douban = mGson.fromJson(response, Douban.class);
                listener.onResponse(douban);
            }
        });
    }

    @Override
    public void storeDB(Douban douban) {
        String doubanStr = mGson.toJson(douban);
        ContentValues values = new ContentValues();
        values.put("content", doubanStr);
        values.put("date", douban.getDate());
        values.put("getTime", System.currentTimeMillis() + "");
        mDb.insertWithOnConflict("Douban", null, values, SQLiteDatabase.CONFLICT_REPLACE);
        values.clear();

    }

    @Override
    public Douban getForDB(long date) {
        Cursor douban = mDb.query("Douban", null, null, null, null, null, null);
        String result = "";
        if (douban.moveToFirst()) {
            do {
                String dateR = douban.getString(douban.getColumnIndex("date"));
                if (dateR.equals(DateUtil.doubanDate(date))) {
                    result = douban.getString(douban.getColumnIndex("content"));
                }
            } while (douban.moveToNext());
        }
        return mGson.fromJson(result, Douban.class);
    }
}
