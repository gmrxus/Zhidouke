package com.gmrxus.zhidouke.guoke;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.adapter.GuokeRecyclerViewAdapter;
import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.content.ContentActivity;

import java.util.List;

/**
 * Created by mac on 2017/4/23.
 */

public class GuokeFragment extends Fragment implements GuokeContact.View, SwipeRefreshLayout.OnRefreshListener {
  private GuokeContact.Presenter mPresenter;
  private SwipeRefreshLayout mRefreshLayout;
  private RecyclerView mRecyclerView;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mPresenter = new GuokePresenter(getActivity(), this);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
      savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_guoke, container, false);
    initView(rootView);
    mPresenter.start();
    return rootView;
  }

  private void initView(View view) {
    mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_guoke);
    mRefreshLayout.setOnRefreshListener(this);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_guoke);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
  }

  @Override
  public void setPresenter(GuokeContact.Presenter presenter) {
    this.mPresenter = presenter;
  }

  @Override
  public void showResult(List<Guoke.ResultBean> guokeListBean) {
    GuokeRecyclerViewAdapter adapter = new GuokeRecyclerViewAdapter(getActivity(), guokeListBean);
    mRecyclerView.setAdapter(adapter);
    adapter.setOnItemClickListener(new GuokeRecyclerViewAdapter.OnItemClickListener() {
      @Override
      public void click(View view, int position) {
        mPresenter.itemClick(position);
      }
    });
  }

  @Override
  public void showMsg(String msg) {

  }

  public void showLoading(boolean isLoading) {
    mRefreshLayout.setRefreshing(isLoading);
  }

  @Override
  public void readStory(Guoke.ResultBean bean) {
    ContentActivity.in2Activity(getActivity(), bean.getLink(), 3);

  }

  @Override
  public void loadMore() {

  }

  @Override
  public void refresh() {
    mPresenter.loadData();
  }

  @Override
  public void onRefresh() {
    refresh();
  }
}
