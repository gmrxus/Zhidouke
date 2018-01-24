package com.gmrxus.zhidouke.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gmrxus.zhidouke.BaseActivity;
import com.gmrxus.zhidouke.R;

/**
 * Created by Gmrxus on 2018/1/23.
 */

public class SettingActivity extends BaseActivity {
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);
    initView();
    doBusiness();
  }

  private void initView() {
    setSupportActionBar((Toolbar) findViewById(R.id.toolbar_settings));
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  private void doBusiness() {
    SettingFragment settingFragment = new SettingFragment();
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.framelayout_settings, settingFragment)
        .commit();
    new SettingPresenter(this, settingFragment);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      onBackPressed();
    }
    return super.onContextItemSelected(item);
  }
}
