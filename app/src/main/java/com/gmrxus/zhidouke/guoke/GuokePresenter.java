package com.gmrxus.zhidouke.guoke;

import android.content.Context;

import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.util.SpUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.List;


/**
 * Created by Gmrxus on 2018/1/15.
 */

public class GuokePresenter implements GuokeContact.Presenter {
  private GuokeContact.Model mModel;
  private Context mContext;
  private GuokeContact.View mView;
  private Gson mGson;
  private List<Guoke.ResultBean> mResult;


  public GuokePresenter(Context context, GuokeContact.View view) {
    mContext = context;
    mView = view;
    mModel = new GuokeModel();
    mView.setPresenter(this);
    mGson = new Gson();
  }

  @Override
  public void start() {
    loadData();
  }

  @Override
  public void loadData() {
    mModel.loadEntry(new GuokeModel.OnLoadListener() {
      @Override
      public void success(String result) {
        Logger.d(result);
        Guoke guoke = mGson.fromJson(result, Guoke.class);
        mResult = guoke.getResult();
        mView.showResult(mResult);
        mView.showLoading(false);
      }

      @Override
      public void fail(String error) {
        Logger.d(error);
        mView.showLoading(false);
      }
    });
  }

  @Override
  public void itemClick(int position) {
    mView.readStory(mResult.get(position));
    SpUtil.put(mContext, SpUtil.SpKey.GUOKE_IMG, mResult.get(position).getHeadline_img());
    SpUtil.put(mContext, SpUtil.SpKey.GUOKE_TITLE, mResult.get(position).getTitle());
  }
}
