package com.gmrxus.zhidouke.douban;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;
import com.gmrxus.zhidouke.bean.Douban;

import java.util.List;

/**
 * Created by mac on 2017/5/2.
 */

public interface DoubanContract {
  interface View extends BaseView<Presenter> {
    void showResult(List<Douban> doubanList);

    void startLoading();

    void stopLoading();

    void showError(String msg);

    void itemClick();

    void showErrorL(String msg);
  }

  interface Presenter extends BasePresenter {
    void showContent(Douban.PostsBean bean);

    void setModel();

    void detach();

    Douban load(String date, boolean isRefresh);

    void loadMore();

    void refresh();
  }

  interface Model {
    void getForNet(String date, ResultListener listener);

    void storeDB(Douban douban);

    Douban getForDB(long date);

    interface ResultListener {
      void onError();

      void onResponse(Douban douban);
    }
  }
}
