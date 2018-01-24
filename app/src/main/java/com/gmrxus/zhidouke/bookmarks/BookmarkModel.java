package com.gmrxus.zhidouke.bookmarks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmrxus.zhidouke.common.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2018/1/19.
 */

class BookmarkModel {

  public List<String> getBookMarkers(String table) {
    List<String> results = new ArrayList<>();
    SQLiteDatabase rd = MyApplication.dbUtil.getReadableDatabase();
    Cursor zhihu = rd.query(table, new String[]{"content"}, null, null, null, null, null);
    if (zhihu.moveToFirst()) {
      do {
        results.add(zhihu.getString(zhihu.getColumnIndex("content")));
      } while (zhihu.moveToNext());
    }
    return results;
  }
}
