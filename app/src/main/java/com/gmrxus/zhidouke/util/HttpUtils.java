package com.gmrxus.zhidouke.util;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.gmrxus.zhidouke.common.MyApplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by mac on 2017/4/24.
 */

public class HttpUtils {
  private static OkHttpClient mClient = new OkHttpClient();

  private static OnHttpRequestCallBackListener sListener;


  public void setOnHttpRequestCallBackListener(OnHttpRequestCallBackListener listener) {
    this.sListener = listener;
  }

  /**
   * 同步get请求
   *
   * @param url
   * @return
   */
  public static String getT(String url) {
    Request request = new Request.Builder().get().url(url).build();
    Call call = mClient.newCall(request);
    try {
      String result = call.execute().body().string();
      return result;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 异步 get请求
   *
   * @param url
   * @param listener
   */
  public static void getY(String url, final OnHttpRequestCallBackListener listener) {
    final Request request = new Request.Builder().get().url(url).build();
    Call call = mClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        listener.onFailure(e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        listener.onResponse(response.body().string());
      }
    });
  }

  public interface OnHttpRequestCallBackListener {
    void onFailure(IOException e);

    void onResponse(String response);
  }

  public interface VolleyCallBackListener {
    void onResponse(String response);

    void onError(VolleyError error);
  }


  public static void getV(String url, final VolleyCallBackListener listener) {
    StringRequest request = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley
        .Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        listener.onResponse(response);
      }
    }, new com.android.volley.Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        listener.onError(error);
      }
    });
    MyApplication.sRequestQueue.add(request);
  }

}
