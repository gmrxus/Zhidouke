package com.gmrxus.zhidouke.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Gmrxus on 2018/1/15.
 */

public class SpUtil {
  private static final String SP_NAME = "config";

  public static void put(Context context, String key, String value) {
    SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    sp.edit().putString(key, value).commit();
  }

  public static String get(Context context, String key, String def) {
    String string = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, def);
    return string;
  }

  public static void put(Context context, String key, boolean b) {
    SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    sp.edit().putBoolean(key, b).commit();
  }

  public static boolean get(Context context, String key, boolean def) {
    boolean aBoolean = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(key, def);
    return aBoolean;
  }

  public interface SpKey {
    String GUOKE_IMG = "guoke_img";
    String GUOKE_TITLE = "guoke_title";
    String CONFIG_NOIMG = "no_img";
    String CONFIG_INLAY_BROWSER = "lnlay_browser";
  }
}
