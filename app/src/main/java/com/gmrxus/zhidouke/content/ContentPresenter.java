package com.gmrxus.zhidouke.content;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.webkit.WebView;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.DbBean;
import com.gmrxus.zhidouke.bean.DoubanStory;
import com.gmrxus.zhidouke.bean.Type;
import com.gmrxus.zhidouke.bean.ZhihuInfoBean;
import com.gmrxus.zhidouke.common.FormatContent;
import com.gmrxus.zhidouke.common.Urls;
import com.gmrxus.zhidouke.customtabs.CustomTabActivityHelper;
import com.gmrxus.zhidouke.customtabs.WebviewFallback;
import com.gmrxus.zhidouke.listener.StringListener;
import com.gmrxus.zhidouke.util.SpUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import static com.gmrxus.zhidouke.bean.Type.DOUBAN_CONTENT;
import static com.gmrxus.zhidouke.bean.Type.GUOKE_CONTENT;
import static com.gmrxus.zhidouke.bean.Type.ZHIHU_CONTENT;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public class ContentPresenter implements ContentContract.Presenter {
  private final Gson mGson;
  private final ContentModel mModel;
  private Context mContext;
  private ContentContract.View mView;
  private String mId;
  private Type type;
  private ZhihuInfoBean mZhihuBean;
  private DoubanStory mDoubanBean;
  private String mBeanJson = "";
  private String mContentUrl;

  public ContentPresenter(String id, Context context, ContentContract.View view, Type type) {
    this.type = type;
    mId = id;
    mContext = context;
    mView = view;
    mView.setPresenter(this);
    mModel = new ContentModel();
    mGson = new Gson();
//    start();
  }

  @Override
  public void start() {
    loadResult();
  }


  @Override
  public void loadResult() {
    mView.showLoading();
    if (type == ZHIHU_CONTENT) {//知乎
      loadZhihu();
    } else if (type == DOUBAN_CONTENT) {//豆瓣
      loadDaouban();
    } else if (type == GUOKE_CONTENT) {//果壳
      loadGuoke();
    }
  }


  @Override
  public void openInBrowser() {
    if (TextUtils.isEmpty(mContentUrl)) {
      mView.showMsg("页面没有正确打开");
      return;
    }
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    Uri uri = Uri.parse(mContentUrl);
    intent.setData(uri);
    mContext.startActivity(intent);
  }

  @Override
  public void openUrl(WebView webView, String url) {
    CustomTabsIntent customTabsIntent = new CustomTabsIntent.Builder()
        .setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary))
        .setShowTitle(true)
        .build();

    CustomTabActivityHelper.openCustomTab(
        (Activity) mContext, customTabsIntent, Uri.parse(url), new WebviewFallback());
  }

  @Override
  public void refresh() {
    loadResult();
  }

  @Override
  public void addAndDelBookmarks() {
    if (isBookmarks()) {
      mModel.delForDb(type, mId);
      mView.showMsg("已从收藏中删除");
    } else {
      mModel.addForDb(type, mId, mBeanJson);
      mView.showMsg("已添加到收藏");
    }
  }


  private void loadZhihu() {
    mModel.loadForNet(Urls.ZHIHU_STORY + mId, new StringListener() {
      @Override
      public void onSuccess(String result) {
        Logger.json(result);
        mBeanJson = result;
        mZhihuBean = mGson.fromJson(result, ZhihuInfoBean.class);
        mView.showResult(
            FormatContent.convertZhihuContent(mZhihuBean.getBody(), mContext),
            mZhihuBean.getImage());
        mView.setTitle(mZhihuBean.getTitle());
        mView.setImgRus("图片:" + mZhihuBean.getImage_source());
        mView.stopLoading();
        mContentUrl = mZhihuBean.getShare_url();
      }

      @Override
      public void onFail(String error) {
        mView.showMsg(error);
        mView.stopLoading();

      }
    });
  }

  private void loadDaouban() {
    mModel.loadForNet(Urls.DOUBAN_ARTICLE_DETAIL + mId, new StringListener() {
      @Override
      public void onSuccess(String result) {
        Logger.d(result);
        mBeanJson = result;
        mDoubanBean = mGson.fromJson(result, DoubanStory.class);
        mView.showResult(FormatContent.convertDoubanContent(mDoubanBean.getContent(), mContext),
            mDoubanBean.getThumbs().size() != 0 ? mDoubanBean.getThumbs().get(0).getLarge().getUrl() :
                "https://img3.doubanio.com/lpic/s29441950.jpg");
        mView.setTitle(mDoubanBean.getTitle());
        mView.stopLoading();
        mContentUrl = mDoubanBean.getUrl();
      }

      @Override
      public void onFail(String error) {
        mView.stopLoading();
      }
    });
  }

  private void loadGuoke() {
    mModel.loadForNet(mId, new StringListener() {
      @Override
      public void onSuccess(String result) {
        Logger.d(result);
        mView.showResult(result, SpUtil.get(mContext, SpUtil.SpKey.GUOKE_IMG, ""));
        mView.setTitle(SpUtil.get(mContext, SpUtil.SpKey.GUOKE_TITLE, ""));
        mView.stopLoading();
        mContentUrl = mId;
      }

      @Override
      public void onFail(String error) {
        Logger.d(error);
        mView.stopLoading();
      }
    });
  }

  @Override
  public boolean isBookmarks() {
    if (type == GUOKE_CONTENT) {
      // TODO: 2018/1/25 果壳的收藏功能
      return false;
    }
    ArrayList<DbBean> results = (ArrayList<DbBean>) mModel.queryForDb(type, mId);
    if (!results.isEmpty()) {
      for (DbBean bean : results) {
        if (mId.equals(bean.getId())) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void copyLink() {
    if (TextUtils.isEmpty(mContentUrl)) {
      mView.showMsg("页面没有正确打开");
      return;
    }
    ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
    ClipData cd = cm.getPrimaryClip();
    cd.addItem(new ClipData.Item(mContentUrl));
    cm.setPrimaryClip(cd);
    mView.showMsg("已复制到剪切板");
  }

  @Override
  public void share() {
    if (TextUtils.isEmpty(mContentUrl)) {
      mView.showMsg("页面没有正确打开");
      return;
    }
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, mContentUrl);
    mContext.startActivity(Intent.createChooser(intent, "分享至"));

  }
}
