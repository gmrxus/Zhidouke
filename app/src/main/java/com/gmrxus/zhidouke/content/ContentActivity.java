package com.gmrxus.zhidouke.content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gmrxus.zhidouke.BaseActivity;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.Type;

/**
 * Created by Gmrxus on 2018/1/5.
 */

public class ContentActivity extends BaseActivity {
  public static final String ID = "url";
  public static final String PAGE_TYPE = "type";
  private ContentFragment fragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.frame);
    String id = getIntent().getStringExtra(ID);
    Type pid = null;
    int intExtra = getIntent().getIntExtra(PAGE_TYPE, 0);
    if (intExtra == 1) {
      pid = Type.ZHIHU_CONTENT;
    } else if (intExtra == 2) {
      pid = Type.DOUBAN_CONTENT;
    } else if (intExtra == 3) {
      pid = Type.GUOKE_CONTENT;
    }

    if (savedInstanceState != null) {
      fragment = (ContentFragment) getSupportFragmentManager().getFragment(savedInstanceState, "ContentFragment");
    } else {
      fragment = new ContentFragment();
      getSupportFragmentManager().beginTransaction().add(R.id.frame, fragment).commit();
    }
    ContentPresenter contentPresenter = new ContentPresenter(id, this, fragment, pid);
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (fragment.isAdded()) {
      getSupportFragmentManager().putFragment(outState, "ContentFragment", fragment);
    }
  }


  public static void in2Activity(Activity activity, String id, int type) {
    Intent intent = new Intent(activity, ContentActivity.class);
    intent.putExtra(ID, id);
    intent.putExtra(PAGE_TYPE, type);
    activity.startActivity(intent);
  }
}
