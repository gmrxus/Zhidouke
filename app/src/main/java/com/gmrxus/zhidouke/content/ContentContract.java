package com.gmrxus.zhidouke.content;

import android.webkit.WebView;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;
import com.gmrxus.zhidouke.bean.DbBean;
import com.gmrxus.zhidouke.bean.Type;
import com.gmrxus.zhidouke.listener.StringListener;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public interface ContentContract {
  interface View extends BaseView<Presenter> {
    void showLoading();

    void stopLoading();

    void showMsg(String errorMsg);

    void showResult(String result, String imgUrl);

    void setTitle(String title);

    void setImgRus(String imgRus);

    void setBookmarkType(boolean isBookmark);


  }

  interface Presenter extends BasePresenter {

    void loadResult();

    void openInBrowser();

    void openUrl(WebView webView, String url);

    void refresh();

    void addAndDelBookmarks();

    boolean isBookmarks();
  }

  interface Model {

    void loadForNet(String url, StringListener listener);

    void addForDb(Type type, String id, String content);

    void delForDb(Type type, String id);

    void updateForDb(Type type, String id);

    List<DbBean> queryForDb(Type type, String id);
  }
}
