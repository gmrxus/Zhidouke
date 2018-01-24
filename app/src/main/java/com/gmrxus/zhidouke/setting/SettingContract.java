package com.gmrxus.zhidouke.setting;

import com.gmrxus.zhidouke.BasePresenter;
import com.gmrxus.zhidouke.BaseView;

/**
 * Created by Gmrxus on 2018/1/23.
 */

public interface SettingContract {
  interface View extends BaseView<Presenter> {
    void showMsg(String msg);

  }

  interface Presenter extends BasePresenter {
    void setAsNoImg(boolean isNoImg);

    void setAsInlayBrowser(boolean isInlayBrowser);

    void clearImgCache();

    boolean getIsNoImgInitSettings();

    boolean getIsInlayBrowser();
  }

}
