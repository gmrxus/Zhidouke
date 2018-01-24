package com.gmrxus.zhidouke.zhihu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.gmrxus.zhidouke.bean.ZhihuNews;
import com.gmrxus.zhidouke.common.MyApplication;
import com.gmrxus.zhidouke.common.Urls;
import com.gmrxus.zhidouke.util.DateUtil;
import com.gmrxus.zhidouke.util.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/4/24.
 */

public class ZhihuPresenter implements ZhihuContract.Presenter {
  private static final String TAG = "ZhihuPresenter";

  private ZhihuContract.View mView;
  private Context mContext;
  private final Gson mGson;

  private List<ZhihuNews> mZhihuNewList = new ArrayList<>();
  private final SQLiteDatabase mDb;

  public ZhihuPresenter(ZhihuContract.View view, Context context) {
    attach(view);
    view.setPresenter(this);
    mGson = new Gson();
    mDb = MyApplication.dbUtil.getWritableDatabase();

    this.mContext = context;
  }


  @Override
  public void start() {
    load(DateUtil.getZhihuUrlDate(), false);
  }

  @Override
  public void attach(ZhihuContract.View view) {
    this.mView = view;
  }


  @Override
  public void loadMore(String date) {
    load(date, false);
  }

  @Override
  public void load(String date, final boolean isRefresh) {
    if (isRefresh) {
      mZhihuNewList.clear();
    }
    HttpUtils.getY(Urls.ZHIHU_MAIN_URL + date, new HttpUtils.OnHttpRequestCallBackListener() {
      @Override
      public void onFailure(IOException e) {
        mView.stopRefresh();
        mView.showError("网络错误");
        Log.e(TAG, "onFailure: " + e);
      }

      @Override
      public void onResponse(String response) {
        ZhihuNews zhihuNews = mGson.fromJson(response, ZhihuNews.class);
        mZhihuNewList.add(zhihuNews);
        mView.runMainUiThread(new ZhihuFragment.UiThread() {
          @Override
          public void handlerOnUi() {
            mView.showContent(mZhihuNewList);
            if (isRefresh) {
              mView.stopRefresh();
            }
          }
        });
      }
    });
  }

  @Override
  public void addContentForDB(String content) {
    ContentValues values = new ContentValues();
    values.put("content", content);
    values.put("date", mGson.fromJson(content, ZhihuNews.class).getDate());
    values.put("getTime", System.currentTimeMillis() + "");
    mDb.insertWithOnConflict("Zhihu", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    values.clear();
  }

  @Override
  public boolean getContentForNetAndAddDB(String date) {
    return false;
  }

  @Override
  public String getContentForDB(String date) {
    return null;
  }

  @Override
  public void deAttach() {
    mView = null;
  }

}



