package com.gmrxus.zhidouke.setting;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.common.MyApplication;
import com.gmrxus.zhidouke.util.SpUtil;

/**
 * Created by Gmrxus on 2018/1/23.
 */

public class SettingPresenter implements SettingContract.Presenter {

  private Context mContext;
  private SettingContract.View mView;

  public SettingPresenter(Context context, SettingContract.View view) {
    mContext = context;
    mView = view;
    mView.setPresenter(this);
  }

  @Override
  public void start() {
  }


  @Override
  public void setAsNoImg(boolean isNoImg) {
    SpUtil.put(mContext, SpUtil.SpKey.CONFIG_NOIMG, isNoImg);
  }

  @Override
  public void setAsInlayBrowser(boolean isInlayBrowser) {
    SpUtil.put(mContext, SpUtil.SpKey.CONFIG_INLAY_BROWSER, isInlayBrowser);
  }

  @Override
  public void clearImgCache() {
    Glide.get(mContext).clearMemory();
    new Thread(new Runnable() {
      @Override
      public void run() {
        Glide.get(MyApplication.context).clearDiskCache();
        mView.showMsg(mContext.getResources().getString(R.string.clear_image_cache));
      }
    }).start();
  }

  @Override
  public boolean getIsNoImgInitSettings() {
    return SpUtil.get(mContext, SpUtil.SpKey.CONFIG_NOIMG, false);

  }

  @Override
  public boolean getIsInlayBrowser() {
    return SpUtil.get(mContext, SpUtil.SpKey.CONFIG_INLAY_BROWSER, true);
  }
}
