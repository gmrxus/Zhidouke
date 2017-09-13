package com.gmrxus.zhidouke.douban;

import android.content.Context;

import com.gmrxus.zhidouke.bean.Douban;
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


    public DoubanPresenter(DoubanContract.View view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void start() {
        refresh();
    }

    @Override
    public void setModel() {
        this.mModel = new DoubanModel(mContext);
    }


    @Override
    public void dettach() {
        mView = null;
        mModel = null;
    }

    @Override
    public Douban load(long date, final boolean isRefresh) {
        if (isRefresh) {
            mView.startLoading();
        }
        if (NetworkUtils.isConnected(mContext)) {
            mModel.getForNet(date, new DoubanContract.Model.ResultListener() {
                @Override
                public void onError() {

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

        }
        return null;
    }

    @Override
    public void loadMore(long thisDate) {
        load(thisDate, false);
    }

    @Override
    public void refresh() {
        load(System.currentTimeMillis(), true);
    }
}
