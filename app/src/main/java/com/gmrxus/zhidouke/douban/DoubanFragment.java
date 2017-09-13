package com.gmrxus.zhidouke.douban;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.Douban;

import java.util.List;

/**
 * Created by mac on 2017/4/23.
 */

public class DoubanFragment extends Fragment implements DoubanContract.View {
    private static final String TAG = "DoubanFragment";

    private DoubanContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_douban, container, false);
        return rootView;
    }

    @Override
    public void setPresenter(DoubanContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showResult(List<Douban> doubanList) {

    }

    @Override
    public void startLoading() {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showError() {

    }
}
