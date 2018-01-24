package com.gmrxus.zhidouke.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.util.SnackbarUtil;


/**
 * Created by Gmrxus on 2018/1/23.
 */

public class SettingFragment extends Fragment implements SettingContract.View, View.OnClickListener, CompoundButton
    .OnCheckedChangeListener {

  private SettingContract.Presenter mPresenter;
  private RelativeLayout rlNoImg;
  private RelativeLayout rlInlayBrowser;
  private RelativeLayout rlClearCache;
  private CheckBox cbNoImg;
  private CheckBox cbInlayBrowser;
  private View pView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
      savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    initView(view);
    return view;
  }

  private void initView(View view) {
    pView = view.findViewById(R.id.p_view);
    rlNoImg = (RelativeLayout) view.findViewById(R.id.rl_setting_no_img);
    rlInlayBrowser = (RelativeLayout) view.findViewById(R.id.rl_setting_inlay_browser);
    rlClearCache = (RelativeLayout) view.findViewById(R.id.rl_setting_clear_cache);
    cbNoImg = (CheckBox) view.findViewById(R.id.cb_setting_no_img);
    cbInlayBrowser = (CheckBox) view.findViewById(R.id.cb_setting_inlay_browser);
    rlNoImg.setOnClickListener(this);
    rlInlayBrowser.setOnClickListener(this);
    rlClearCache.setOnClickListener(this);
    cbNoImg.setOnCheckedChangeListener(this);
    cbInlayBrowser.setOnCheckedChangeListener(this);
    cbNoImg.setChecked(mPresenter.getIsNoImgInitSettings());
    cbInlayBrowser.setChecked(mPresenter.getIsInlayBrowser());
  }

  @Override
  public void showMsg(String msg) {
    SnackbarUtil.textSnackbar(pView, msg);
  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.rl_setting_no_img:
        cbNoImg.setChecked(!cbNoImg.isChecked());
        break;
      case R.id.rl_setting_inlay_browser:
        cbInlayBrowser.setChecked(!cbInlayBrowser.isChecked());
        break;
      case R.id.rl_setting_clear_cache:
        mPresenter.clearImgCache();
        break;
    }
  }

  @Override
  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    if (buttonView.getId() == R.id.cb_setting_no_img) {
      mPresenter.setAsNoImg(isChecked);
    } else if (buttonView.getId() == R.id.cb_setting_inlay_browser) {
      mPresenter.setAsInlayBrowser(isChecked);
    }
  }

  @Override
  public void setPresenter(SettingContract.Presenter presenter) {
    mPresenter = presenter;
  }
}
