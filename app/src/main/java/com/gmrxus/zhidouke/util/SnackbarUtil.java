package com.gmrxus.zhidouke.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Gmrxus on 2017/9/14.
 */

public class SnackbarUtil {
  public static void textSnackbar(View pView, String msg) {
    Snackbar.make(pView, msg, Snackbar.LENGTH_SHORT).show();
  }
}
