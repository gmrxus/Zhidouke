package com.gmrxus.zhidouke.content;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.android.volley.VolleyError;
import com.gmrxus.zhidouke.bean.DbBean;
import com.gmrxus.zhidouke.bean.Type;
import com.gmrxus.zhidouke.common.MyApplication;
import com.gmrxus.zhidouke.listener.StringListener;
import com.gmrxus.zhidouke.util.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public class ContentModel implements ContentContract.Model {

  @Override
  public void loadForNet(String url, final StringListener listener) {
    HttpUtils.getV(url, new HttpUtils.VolleyCallBackListener() {
      @Override
      public void onResponse(String response) {
        listener.onSuccess(response);
      }

      @Override
      public void onError(VolleyError error) {
        listener.onFail(error.getMessage());
      }
    });
  }

  @Override
  public void addForDb(Type type, String id, String content) {
    SQLiteDatabase wd = MyApplication.dbUtil.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("date", id);
    values.put("content", content);
    Date date = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String format = simpleDateFormat.format(date);
    values.put("getTime", format);
    values.put("bookmark", 1);
    wd.insert(getTableForType(type), null, values);
  }

  @Override
  public void delForDb(Type type, String id) {
    SQLiteDatabase wd = MyApplication.dbUtil.getWritableDatabase();
    wd.delete(getTableForType(type), "date="+id, null);
//    wd.rawQuery("DELETE FROM " + getTableForType(type) + " WHERE dete = \"" + id+"\"", null);
    wd.close();
  }

  @Override
  public void updateForDb(Type type, String id) {

  }

  @Override
  public List<DbBean> queryForDb(Type type, String id) {
    ArrayList<DbBean> resultList = new ArrayList<>();
    SQLiteDatabase rd = MyApplication.dbUtil.getReadableDatabase();
    String table = getTableForType(type);
    Cursor result = rd.query(table, new String[]{"date", "content",}, id, null, null, null, null);
    int columnCount = result.getColumnCount();
    if (result.moveToFirst()) {
      do {
        DbBean dbBean = new DbBean();
        dbBean.setId(result.getString(result.getColumnIndex("date")));
        dbBean.setContent(result.getString(result.getColumnIndex("content")));
        resultList.add(dbBean);
      } while (result.moveToNext());
    }
    result.close();
    return resultList;

  }

  @NonNull
  private String getTableForType(Type type) {
    String table = "";
    if (type == Type.ZHIHU_CONTENT) {
      table = "Zhihu";
    } else if (type == Type.DOUBAN_CONTENT) {
      table = "Douban";
    } else if (type == Type.GUOKE_CONTENT) {
      table = "Guoke";
    }
    return table;
  }
}
