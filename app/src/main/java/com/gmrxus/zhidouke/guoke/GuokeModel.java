package com.gmrxus.zhidouke.guoke;

import com.android.volley.VolleyError;
import com.gmrxus.zhidouke.common.Urls;
import com.gmrxus.zhidouke.util.HttpUtils;

/**
 * Created by Gmrxus on 2018/1/15.
 */

public class GuokeModel implements GuokeContact.Model {
  public void loadEntry(final OnLoadListener listener) {
    String url = Urls.GUOKR_ARTICLES;
    HttpUtils.getV(url, new HttpUtils.VolleyCallBackListener() {
      @Override
      public void onResponse(String response) {
        listener.success(response);
      }

      @Override
      public void onError(VolleyError error) {
        listener.fail(error.getMessage());
      }
    });
  }

  interface OnLoadListener {
    void success(String result);

    void fail(String error);
  }
}
