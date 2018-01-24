package com.gmrxus.zhidouke.bookmarks;

import android.content.Context;

import com.gmrxus.zhidouke.adapter.BookmarkRecyclerAdapter;
import com.gmrxus.zhidouke.bean.DoubanStory;
import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.bean.ZhihuInfoBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2018/1/19.
 */

public class BookmarkerPresenter implements BookmarkContact.Presenter {
  private BookmarkModel mModel;
  private Context mContext;
  private Gson mGson;
  private List<ZhihuInfoBean> mZhihuInfoBeans;
  private List<DoubanStory> mDoubanStories;
  private List<Guoke.ResultBean> mGuokeInofBeans;
  private List<Integer> markerTypes;
  private BookmarkContact.View mView;


  public BookmarkerPresenter(Context context, BookmarkContact.View view) {
    mContext = context;
    mView = view;
    mModel = new BookmarkModel();
    mGson = new Gson();
    mZhihuInfoBeans = new ArrayList<>();
    mDoubanStories = new ArrayList<>();
    mGuokeInofBeans = new ArrayList<>();
    markerTypes = new ArrayList<>();
    mView.setPresenter(this);
  }

  @Override
  public void start() {
    loadBookmarks();
  }

  @Override
  public void loadBookmarks() {
    mZhihuInfoBeans.clear();
    mDoubanStories.clear();
    mGuokeInofBeans.clear();
    markerTypes.clear();
    List<String> zhihuResult = mModel.getBookMarkers("Zhihu");
//    List<String> guokeResult = mModel.getBookMarkers("Guoke");
    List<String> doubanResult = mModel.getBookMarkers("Douban");

    markerTypes.add(BookmarkRecyclerAdapter.TITILE_ZHIHU);
    if (!zhihuResult.isEmpty()) {
      for (String s : zhihuResult) {
        mZhihuInfoBeans.add(mGson.fromJson(s, ZhihuInfoBean.class));
        markerTypes.add(BookmarkRecyclerAdapter.MARKER_TYPE_ZHIHU);
      }
    }
    markerTypes.add(BookmarkRecyclerAdapter.TITLE_DOUBAN);
    if (!doubanResult.isEmpty()) {
      for (String s : doubanResult) {
        mDoubanStories.add(mGson.fromJson(s, DoubanStory.class));
        markerTypes.add(BookmarkRecyclerAdapter.MARKER_TYPE_DOUBAN);
      }
    }
    markerTypes.add(BookmarkRecyclerAdapter.TITLE_GUOKE);
//    if (!guokeResult.isEmpty()) {
//      for (String s : guokeResult) {
//        mGuokeInofBeans.add(mGson.fromJson(s, Guoke.ResultBean.class));
//        markerTypes.add(BookmarkRecyclerAdapter.MARKER_TYPE_GUOKE);
//      }
//    }
    mView.showBookmarks(mZhihuInfoBeans, mDoubanStories, mGuokeInofBeans, markerTypes);
  }

  @Override
  public void search(String keywords) {

  }
}
