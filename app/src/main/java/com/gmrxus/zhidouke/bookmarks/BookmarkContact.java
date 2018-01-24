package com.gmrxus.zhidouke.bookmarks;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;
import com.gmrxus.zhidouke.bean.DoubanStory;
import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.bean.ZhihuInfoBean;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/18.
 */

public class BookmarkContact {
  interface Presenter extends BasePresenter {
    void loadBookmarks();

    void search(String keywords);
  }

  interface View extends BaseView<Presenter> {
    void showBookmarks(List<ZhihuInfoBean> zhihuInfoBeans,
                       List<DoubanStory> doubanStories,
                       List<Guoke.ResultBean> guokeInofBeans,
                       List<Integer> markerTypes);

    void showMsg(String msg);

    void showSearchResult();
  }

  interface Model {
    void getBookarks();
  }
}
