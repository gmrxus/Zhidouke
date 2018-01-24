package com.gmrxus.zhidouke.douban;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.adapter.DoubanRecyclerViewAdapter;
import com.gmrxus.zhidouke.bean.Douban;
import com.gmrxus.zhidouke.common.MyApplication;
import com.gmrxus.zhidouke.content.ContentActivity;
import com.gmrxus.zhidouke.util.CalendarUtil;
import com.gmrxus.zhidouke.util.DateUtil;
import com.gmrxus.zhidouke.util.SnackbarUtil;
import com.gmrxus.zhidouke.widget.MyDividerItemDecoration;

import java.util.List;

/**
 * Created by mac on 2017/4/23.
 */

public class DoubanFragment extends Fragment implements DoubanContract.View, SwipeRefreshLayout.OnRefreshListener {
  private static final String TAG = "DoubanFragment";

  private DoubanContract.Presenter mPresenter;
  private LinearLayout pView;
  private RecyclerView mRecyclerView;
  private SwipeRefreshLayout mRefreshLayout;
  private DoubanRecyclerViewAdapter mAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //设置豆瓣一刻接口的延迟时间(豆瓣一刻已经停止更新,接口随时间改变)
    MyApplication.doubanDelayDate = CalendarUtil.sub("2017-08-16", DateUtil.doubanDate(System.currentTimeMillis()));
    mPresenter = new DoubanPresenter(this, getActivity());
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
      savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_douban, container, false);
    initView(rootView);
    return rootView;
  }

  private void initView(View rootView) {
    pView = (LinearLayout) rootView.findViewById(R.id.p_view);
    mRecyclerView = (RecyclerView) rootView.findViewById(R.id.douban_recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//    DividerItemDecoration decor = new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL);
//    decor.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ba_item_line));
    mRecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), MyDividerItemDecoration.VERTICAL_LIST));
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findLastVisibleItemPosition();
        if (newState == 0 && position == mAdapter.getItemCount() - 1) {
          new CountDownTimer(1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
              mPresenter.loadMore();
            }
          }.start();
        }
      }

      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
      }
    });

    mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.douban_swipeRefreshLayout);
    mRecyclerView.setHasFixedSize(true);
    mRefreshLayout.setOnRefreshListener(this);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mPresenter.start();
  }


  @Override
  public void setPresenter(DoubanContract.Presenter presenter) {
    this.mPresenter = presenter;
  }

  @Override
  public void showResult(List<Douban> doubanList) {
    if (mAdapter == null) {
      mAdapter = new DoubanRecyclerViewAdapter(getActivity(), doubanList);
      mRecyclerView.setAdapter(mAdapter);
    } else {
      mAdapter.addContents(doubanList);
      mAdapter.notifyDataSetChanged();
    }
    mAdapter.setOnItemClickListener(new DoubanRecyclerViewAdapter.OnItemClickListener() {
      @Override
      public void click(int position, Douban.PostsBean bean) {
        ContentActivity.in2Activity(getActivity(), String.valueOf(bean.getId()), 2);
      }
    });
  }

  @Override
  public void startLoading() {
    mRefreshLayout.setRefreshing(true);
  }

  @Override
  public void stopLoading() {
    mRefreshLayout.setRefreshing(false);
  }

  @Override
  public void showError(String msg) {
    SnackbarUtil.textSnackbar(pView, msg);
  }

  @Override
  public void itemClick() {

  }

  @Override
  public void onDetach() {
    super.onDetach();
    mPresenter.detach();
  }

  @Override
  public void onRefresh() {
    mPresenter.refresh();
  }
}
