package com.gmrxus.zhidouke.zhihu;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;
import com.gmrxus.zhidouke.bean.ZhihuNews;

import java.util.List;

/**
 * Created by mac on 2017/4/24.
 */

public interface ZhihuContract {
  interface View extends BaseView<Presenter> {

    void showContent(List<ZhihuNews> zhihuNewses);

    void showError(String msg);

    void showProgress();


    void stopRefresh();


    void runMainUiThread(ZhihuFragment.UiThread impl);
  }

  interface Presenter extends BasePresenter {
    void attach(View view);

    void loadMore(String date);

    void load(String date, boolean isRefresh);

    void addContentForDB(String content);

    boolean getContentForNetAndAddDB(String date);

    String getContentForDB(String date);

    void deAttach();

  }
}
