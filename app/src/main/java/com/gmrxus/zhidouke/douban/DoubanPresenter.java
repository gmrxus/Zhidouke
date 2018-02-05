package com.gmrxus.zhidouke.douban;

import android.content.Context;
import android.content.Intent;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.Douban;
import com.gmrxus.zhidouke.content.ContentActivity;
import com.gmrxus.zhidouke.util.DateUtil;
import com.gmrxus.zhidouke.util.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/5/2.
 */

public class DoubanPresenter implements DoubanContract.Presenter {
  private DoubanContract.View mView;
  private Context mContext;
  private DoubanModel mModel;
  private List<Douban> mDoubanList = new ArrayList<>();
  private long doubanReuqestTime;


  public DoubanPresenter(DoubanContract.View view, Context context) {
    this.mView = view;
    this.mContext = context;
    this.doubanReuqestTime = System.currentTimeMillis();
    setModel();
    mView.setPresenter(this);
  }

  @Override
  public void start() {
    refresh();
  }

  @Override
  public void showContent(Douban.PostsBean bean) {
    Intent intent = new Intent(mContext, ContentActivity.class);
    mContext.startActivity(intent);
  }

  @Override
  public void setModel() {
    this.mModel = new DoubanModel(mContext);
  }


  @Override
  public void detach() {
    mView = null;
    mModel = null;
  }

  @Override
  public Douban load(String date, final boolean isRefresh) {
    if (isRefresh) {
      mView.startLoading();
    }
    if (NetworkUtils.isConnected(mContext)) {
      mModel.getForNet(date, new DoubanContract.Model.ResultListener() {
        @Override
        public void onError() {
          mView.showError("网络请求错误");
        }

        @Override
        public void onResponse(Douban douban) {
          if (isRefresh) {
            mDoubanList.clear();
          }
          mDoubanList.add(douban);
          mView.showResult(mDoubanList);
          mView.stopLoading();
        }
      });
    } else {
      mView.showError(mContext.getResources().getString(R.string.net_error));
    }
    return null;
  }

  @Override
  public void loadMore() {
    doubanReuqestTime = doubanReuqestTime - 1000 * 3600 * 24;
    load(DateUtil.doubanDate(doubanReuqestTime), false);
  }

  @Override
  public void refresh() {
    doubanReuqestTime = System.currentTimeMillis();
    load(DateUtil.doubanDate(doubanReuqestTime), true);
  }
}
