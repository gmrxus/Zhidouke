package com.gmrxus.zhidouke.bookmarks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.adapter.BookmarkRecyclerAdapter;
import com.gmrxus.zhidouke.bean.DoubanStory;
import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.bean.ZhihuInfoBean;
import com.gmrxus.zhidouke.content.ContentActivity;
import com.gmrxus.zhidouke.util.SnackbarUtil;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/18.
 */

public class BookmarkFragment extends Fragment implements BookmarkContact.View {

  private View mPv;
  private RecyclerView mRecyclerView;
  private BookmarkContact.Presenter mPresenter;
  private BookmarkRecyclerAdapter mAdapter;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
      savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
    initView(view);
    doBusiness();
    return view;
  }

  private void initView(View view) {
    mPv = view.findViewById(R.id.p_view);
    mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_bookmark);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

  }

  private void doBusiness() {

  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);
    if (!hidden) {
      mPresenter.start();
    }
  }

  @Override
  public void setPresenter(BookmarkContact.Presenter presenter) {
    mPresenter = presenter;
  }


  @Override
  public void showBookmarks(List<ZhihuInfoBean> zhihuInfoBeans,
                            List<DoubanStory> doubanStories,
                            List<Guoke.ResultBean> guokeInofBeans,
                            List<Integer> markerTypes) {
    mAdapter = new BookmarkRecyclerAdapter(getActivity(),
        zhihuInfoBeans,
        doubanStories,
        guokeInofBeans,
        markerTypes);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener(new BookmarkRecyclerAdapter.OnItemClickListener() {
      @Override
      public void itemClick(int id, int pageType) {
        ContentActivity.in2Activity(getActivity(), String.valueOf(id), pageType);
      }
    });

  }

  @Override
  public void showMsg(String msg) {
    SnackbarUtil.textSnackbar(mPv, msg);
  }

  @Override
  public void showSearchResult() {

  }
}
