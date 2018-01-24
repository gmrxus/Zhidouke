package com.gmrxus.zhidouke.guoke;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;
import com.gmrxus.zhidouke.bean.Guoke;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/12.
 */

public interface GuokeContact {
  interface View extends BaseView<Presenter> {
    void showResult(List<Guoke.ResultBean> guokeListBean);

    void showMsg(String msg);

    void showLoading(boolean isLoading);

    void readStory(Guoke.ResultBean bean);

    void loadMore();

    void refresh();
  }

  interface Presenter extends BasePresenter {
    void loadData();

    void itemClick(int position);
  }

  interface Model {
    void loadEntry(GuokeModel.OnLoadListener listener);
  }
}
