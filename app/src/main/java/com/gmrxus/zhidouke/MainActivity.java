package com.gmrxus.zhidouke;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gmrxus.zhidouke.bookmarks.BookmarkFragment;
import com.gmrxus.zhidouke.bookmarks.BookmarkerPresenter;
import com.gmrxus.zhidouke.homepage.MainFragment;
import com.gmrxus.zhidouke.setting.SettingActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
  private static final String TAG = "MainActivity";

  private DrawerLayout drawableLayout;
  private Toolbar toolbar;
  private ActionBarDrawerToggle mToggle;
  private NavigationView navigation;
  private MainFragment mMainFragment;
  private BookmarkFragment mBookmarkFragment;
  private FragmentTransaction mFt;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initView();
  }

  private void initView() {
    navigation = (NavigationView) findViewById(R.id.nav_main);
    drawableLayout = (DrawerLayout) findViewById(R.id.drawableLayout);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setTitle(getResources().getString(R.string.app_name));
    setSupportActionBar(toolbar);
    mToggle = new ActionBarDrawerToggle(this, drawableLayout, toolbar, R.string.draw_open, R
        .string.draw_close);
    drawableLayout.setDrawerListener(mToggle);
    mToggle.syncState();
    navigation.setNavigationItemSelectedListener(this);
    addFragments();
//    showMainFragment();

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.toolbar_setting, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_main_page:
        showMainFragment();
        break;
      case R.id.menu_bookmark:
        showBookmarkFragment();
        break;
//      case R.id.menu_theme:
//        changeTheme();
//        break;
      case R.id.menu_setting:
        startActivity(new Intent(this, SettingActivity.class));
        break;
      case R.id.menu_about:
        break;
    }
    drawableLayout.closeDrawer(navigation);
    return true;
  }

  private void addFragments() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    mMainFragment = new MainFragment();
    mBookmarkFragment = new BookmarkFragment();
    new BookmarkerPresenter(this, mBookmarkFragment);
    ft.add(R.id.frameLayout, mBookmarkFragment, "bookmarkFragment");
    ft.add(R.id.frameLayout, mMainFragment, "mainFragment");
    ft.hide(mBookmarkFragment);
    ft.commit();

  }

  private void showMainFragment() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.show(mMainFragment);
    ft.hide(mBookmarkFragment);
    ft.commit();
    toolbar.setTitle("知豆壳");
  }

  private void showBookmarkFragment() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.show(mBookmarkFragment);
    ft.hide(mMainFragment);
    ft.commit();
    toolbar.setTitle("收藏");
  }


  private void changeTheme() {
    drawableLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
      @Override
      public void onDrawerSlide(View drawerView, float slideOffset) {

      }

      @Override
      public void onDrawerOpened(View drawerView) {

      }

      @Override
      public void onDrawerClosed(View drawerView) {
        SharedPreferences sp = getSharedPreferences("user_settings", MODE_PRIVATE);
        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
            == Configuration.UI_MODE_NIGHT_YES) {
          sp.edit().putInt("theme", 0).apply();
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
          sp.edit().putInt("theme", 1).apply();
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
      }

      @Override
      public void onDrawerStateChanged(int newState) {

      }
    });
  }
}