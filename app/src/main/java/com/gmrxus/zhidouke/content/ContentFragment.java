package com.gmrxus.zhidouke.content;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.util.SnackbarUtil;
import com.gmrxus.zhidouke.util.SpUtil;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public class ContentFragment extends Fragment implements ContentContract.View {
  private Context context;
  private ImageView iv;
  private SwipeRefreshLayout srl;
  private ContentContract.Presenter mPresenter;
  private CoordinatorLayout pView;
  private WebView wv;
  private CollapsingToolbarLayout toolbarLayou;
  private TextView tvImgRus;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    context = getContext();
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
      savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_content, container, false);
    initView(rootView);
    mPresenter.start();
    return rootView;
  }

  private void initView(View rootView) {
    setHasOptionsMenu(true);
    pView = (CoordinatorLayout) rootView.findViewById(R.id.p_view);
    final NestedScrollView scrollBar = rootView.findViewById(R.id.scrollView);
    toolbarLayou = (CollapsingToolbarLayout) rootView.findViewById(R.id.ctl_content);
    Toolbar toolbar = rootView.findViewById(R.id.toolbar_content);
    ContentActivity activity = (ContentActivity) getActivity();
    activity.setSupportActionBar(toolbar);
    activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        scrollBar.smoothScrollTo(0, 0);
      }
    });
    toolbar.inflateMenu(R.menu.toolbar_more);
    iv = (ImageView) rootView.findViewById(R.id.iv_content);
    tvImgRus = (TextView) rootView.findViewById(R.id.tv_content);
    srl = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_content);

    wv = (WebView) rootView.findViewById(R.id.wv_content);
    //能够和js交互
    wv.getSettings().setJavaScriptEnabled(true);
    //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
    wv.getSettings().setBuiltInZoomControls(false);
    //缓存
    wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    //开启DOM storage API功能
    wv.getSettings().setDomStorageEnabled(true);
    //开启application Cache功能
    wv.getSettings().setAppCacheEnabled(false);
    //是否是无图模式浏览
    wv.getSettings().setBlockNetworkImage(SpUtil.get(getActivity(), SpUtil.SpKey.CONFIG_NOIMG, false));

//    if (SpUtil.get(getActivity(), SpUtil.SpKey.CONFIG_INLAY_BROWSER, true)) {

//    }else {

//    }


  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.toolbar_more, menu);
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        getActivity().onBackPressed();
        break;
      case R.id.menu_more:
        showMoreDialog();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void setPresenter(ContentContract.Presenter presenter) {
    mPresenter = presenter;
  }

  @Override
  public void showLoading() {
    if (!srl.isRefreshing()) {
      srl.setRefreshing(true);
    }
  }

  @Override
  public void stopLoading() {
    if (srl.isRefreshing()) {
      srl.setRefreshing(false);
    }
  }

  @Override
  public void showMsg(String errorMsg) {
    SnackbarUtil.textSnackbar(pView, errorMsg);
  }

  @Override
  public void showResult(String result, String imgUrl) {
    Glide.with(context)
        .load(imgUrl)
        .asBitmap()
        .placeholder(R.drawable.placeholder)
        .error(R.drawable.placeholder)
        .centerCrop()
        .into(iv);
    wv.loadDataWithBaseURL("x-data://base", result, "text/html", "utf-8", null);
    if (SpUtil.get(getContext(), SpUtil.SpKey.CONFIG_INLAY_BROWSER, true)) {
      wv.setWebViewClient(new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
          mPresenter.openUrl(view, url);
          return true;
        }
      });
    }
    srl.setEnabled(false);

  }

  @Override
  public void setTitle(String title) {
    toolbarLayou.setTitle(title);
    toolbarLayou.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
    toolbarLayou.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
//    toolbarLayou.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
//    toolbarLayou.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
  }

  @Override
  public void setImgRus(String imgRus) {
    tvImgRus.setText(imgRus);
  }

  @Override
  public void setBookmarkType(boolean isBookmark) {
    if (isBookmark) {

    }
  }

  private void showMoreDialog() {
    final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bottom_content, null);
    view.findViewById(R.id.layout_bookmark).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.dismiss();
        mPresenter.addAndDelBookmarks();
      }
    });
    ImageView iv = (ImageView) view.findViewById(R.id.iv_dialog_bookmark);
    TextView tv = (TextView) view.findViewById(R.id.tv_dialog_bookmark);
    boolean bookmarks = mPresenter.isBookmarks();
    if (bookmarks) {
      iv.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorPrimary));
      tv.setText(getString(R.string.delBookmark));
    } else {
      iv.setColorFilter(ContextCompat.getColor(getContext(), R.color.gray));
      tv.setText(getString(R.string.action_add_to_bookmarks));
    }
    dialog.setContentView(view);
    dialog.show();
  }
}
