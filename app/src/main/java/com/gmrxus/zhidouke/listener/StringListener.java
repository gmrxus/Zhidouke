package com.gmrxus.zhidouke.listener;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public interface StringListener {
  void onSuccess(String result);

  void onFail(String error);
}
