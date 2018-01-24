package com.gmrxus.zhidouke.common;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public class FormatContent {
  public static String convertZhihuContent(String preResult, Context context) {

    preResult = preResult.replace("<div class=\"img-place-holder\">", "");
    preResult = preResult.replace("<div class=\"headline\">", "");

    // 在api中，css的地址是以一个数组的形式给出，这里需要设置
    // in fact,in api,css addresses are given as an array
    // api中还有js的部分，这里不再解析js
    // javascript is included,but here I don't use it
    // 不再选择加载网络css，而是加载本地assets文件夹中的css
    // use the css file from local assets folder,not from network
    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";


    // 根据主题的不同确定不同的加载内容
    // load content judging by different theme
    String theme = "<body className=\"\" onload=\"onLoaded()\">";
    if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
        == Configuration.UI_MODE_NIGHT_YES) {
      theme = "<body className=\"\" onload=\"onLoaded()\" class=\"night\">";
    }

    return new StringBuilder()
        .append("<!DOCTYPE html>\n")
        .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
        .append("<head>\n")
        .append("\t<meta charset=\"utf-8\" />")
        .append(css)
        .append("\n</head>\n")
        .append(theme)
        .append(preResult)
        .append("</body></html>").toString();
  }

  public static String convertDoubanContent(String repResult, Context context) {

//    if (doubanMomentStory.getContent() == null) {
//      return null;
//    }
    String css;
    if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK)
        == Configuration.UI_MODE_NIGHT_YES) {
      css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_dark.css\" type=\"text/css\">";
    } else {
      css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
    }
//    String content = doubanMomentStory.getContent();
//    ArrayList<DoubanMomentNews.posts.thumbs> imageList = doubanMomentStory.getPhotos();
//    for (int i = 0; i < imageList.size(); i++) {
//      String old = "<img id=\"" + imageList.get(i).getTag_name() + "\" />";
//      String newStr = "<img id=\"" + imageList.get(i).getTag_name() + "\" "
//          + "src=\"" + imageList.get(i).getMedium().getUrl() + "\"/>";
//      content = content.replace(old, newStr);
//    }
    StringBuilder builder = new StringBuilder();
    builder.append("<!DOCTYPE html>\n");
    builder.append("<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n");
    builder.append("<head>\n<meta charset=\"utf-8\" />\n");
    builder.append(css);
    builder.append("\n</head>\n<body>\n");
    builder.append("<div class=\"container bs-docs-container\">\n");
    builder.append("<div class=\"post-container\">\n");
    builder.append(repResult);
    builder.append("</div>\n</div>\n</body>\n</html>");

    return builder.toString();
  }
}
