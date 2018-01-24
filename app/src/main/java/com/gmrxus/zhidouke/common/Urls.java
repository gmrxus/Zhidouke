package com.gmrxus.zhidouke.common;

/**
 * Created by mac on 2017/4/23.
 */

public class Urls {
  /**
   * 知乎过往消息
   */
  public static final String ZHIHU_MAIN_URL = "http://news-at.zhihu.com/api/4/news/before/";
  public static final String ZHIHU_ITEM_URL = "http://news-at.zhihu.com/api/4/news/";
  public static final String ZHIHU_STORY = "https://news-at.zhihu.com/api/4/news/";

  // 豆瓣一刻
  // 根据日期查询消息列表
  // eg:https://moment.douban.com/api/stream/date/2016-08-11
  public static final String DOUBAN_MAIN_URL = "https://moment.douban.com/api/stream/date/";
  // 获取文章具体内容
  // eg:https://moment.douban.com/api/post/100484
  public static final String DOUBAN_ARTICLE_DETAIL = "https://moment.douban.com/api/post/";

  //果壳精选消息list
  public static final String GUOKR_ARTICLES = "http://apis.guokr.com/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1";
}