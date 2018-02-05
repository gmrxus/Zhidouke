package com.gmrxus.zhidouke.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Gmrxus on 2017/9/14.
 */

public class SnackbarUtil {

  private static Snackbar sMake;

  public static void textSnackbar(View pView, String msg) {
    sMake = Snackbar.make(pView, msg, Snackbar.LENGTH_SHORT);
    sMake.show();
  }

  public static void textSnackbarL(View pView, String msg) {
    sMake = Snackbar.make(pView, msg, Snackbar.LENGTH_LONG);
    sMake.show();
  }

  public static void hide() {
    if (sMake != null) {
      sMake.dismiss();
    }
  }
}
