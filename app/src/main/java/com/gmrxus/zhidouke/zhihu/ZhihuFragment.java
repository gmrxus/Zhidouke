package com.gmrxus.zhidouke.zhihu;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.adapter.MainRecyclerViewAdapter;
import com.gmrxus.zhidouke.bean.ZhihuNews;
import com.gmrxus.zhidouke.util.DateUtil;

import java.util.List;

/**
 * Created by mac on 2017/4/23.
 */

public class ZhihuFragment extends Fragment implements ZhihuContract.View, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ZhihuFragment";

    private ZhihuContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private MainRecyclerViewAdapter mAdapter;
    private LinearLayout mLinearLayout;
    private String mNonceDate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ZhihuPresenter(this, getParentFragment().getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_zhihu, container, false);
        initView(rootView);
        mNonceDate = DateUtil.getZhihuUrlDate();
        mPresenter.start();
        return rootView;
    }

    private void initView(View rootView) {
        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.zhihu_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastItemPosition = lm.findLastVisibleItemPosition();
                if (lastItemPosition == mAdapter.getItemCount() - 1 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mNonceDate = DateUtil.getLastDate(mNonceDate);
                    mPresenter.loadMore(mNonceDate);
                }
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.srl);
        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mAdapter.refresh();
        String zhihuUrlDate = DateUtil.getZhihuUrlDate();
        mNonceDate = zhihuUrlDate;
        mPresenter.load(zhihuUrlDate, true);
    }

    @Override
    public void setPresenter(ZhihuContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public Handler getHandler() {
        return null;
    }

    @Override
    public void showContent(List<ZhihuNews> zhihuNewses) {
        if (mAdapter == null) {
            mAdapter = new MainRecyclerViewAdapter(getContext(), zhihuNewses);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.addContent(zhihuNewses);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError() {
        Snackbar.make(mLinearLayout, "错误", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void stopRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.deAttach();
        
    }

    @Override
    public void runMainUiThread(final UiThread impl) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                impl.handlerOnUi();
            }
        });
    }

    public interface UiThread {
        void handlerOnUi();
    }
}
