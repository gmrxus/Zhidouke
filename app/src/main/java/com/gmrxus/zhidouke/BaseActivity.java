package com.gmrxus.zhidouke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gmrxus.zhidouke.common.MyApplication;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Gmrxus on 2018/1/17.
 */

public class BaseActivity extends AppCompatActivity {

  private static String TAG = "";
  private MyApplication mApp;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    TAG = getClass().getSimpleName();
    mApp = (MyApplication) getApplication();
    mApp.addActivity(this);
    Log.d(TAG, "onCreate: ");

  }



  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart: ");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "onResume: ");
    MobclickAgent.onResume(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    MobclickAgent.onPause(this);
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mApp.removeActivity(this);
  }
}
