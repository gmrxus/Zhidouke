package com.gmrxus.zhidouke;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.gmrxus.zhidouke.homepage.MainFragment;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";

  private DrawerLayout drawableLayout;
  private Toolbar toolbar;
  private ActionBarDrawerToggle mToggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    drawableLayout = (DrawerLayout) findViewById(R.id.drawableLayout);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getResources().getString(R.string.app_name));
    setSupportActionBar(toolbar);
    mToggle = new ActionBarDrawerToggle(this, drawableLayout, toolbar, R.string.draw_open, R
        .string.draw_close);
    drawableLayout.setDrawerListener(mToggle);
    mToggle.syncState();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(R.id.frameLayout, new MainFragment(), "mainFragment");
    ft.commit();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_setting, menu);
    return true;
  }
}