package com.gmrxus.zhidouke.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.gmrxus.zhidouke.db.DBUtil;
import com.idescout.sql.SqlScoutServer;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * Created by mac on 2017/4/26.
 */

public class MyApplication extends Application {
  private static final String TAG = "MyApplication";
  public static Context context;
  public static DBUtil dbUtil;
  public static RequestQueue sRequestQueue;
  public static int doubanDelayDate = 0;
  private ArrayList<Activity> mActivities = new ArrayList<>();

  @Override

  public void onCreate() {
    super.onCreate();
    context = this.getApplicationContext();
    dbUtil = new DBUtil(context);
    SQLiteDatabase db = dbUtil.getWritableDatabase();
    sRequestQueue = Volley.newRequestQueue(this);
    SqlScoutServer.create(this, getPackageName());

  }

  public void addActivity(Activity activity) {
    mActivities.add(activity);
  }

  public void removeActivity(Activity activity) {
    mActivities.remove(activity);
    if (mActivities.size() == 0) {
      Logger.d("db closed");
      dbUtil.close();
    }
  }

  public void removeAll() {
    for (Activity activity : mActivities) {
      mActivities.remove(activity);
    }
  }


}
