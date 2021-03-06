package com.gmrxus.zhidouke.widget;

import android.support.design.widget.AppBarLayout;

/**
 * Created by Gmrxus on 2018/1/8.
 */

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {
  public enum State {
    EXPANDED,
    COLLAPSED,
    IDLE
  }

  private State mCurrentState = State.IDLE;

  @Override
  public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
    if (verticalOffset == 0) {
      if (mCurrentState != State.EXPANDED) {
        onStateChanged(appBarLayout, State.EXPANDED);
      }
      mCurrentState = State.EXPANDED;
    } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
      if (mCurrentState != State.COLLAPSED) {
        onStateChanged(appBarLayout, State.COLLAPSED);
      }
      mCurrentState = State.COLLAPSED;
    } else {
      if (mCurrentState != State.IDLE) {
        onStateChanged(appBarLayout, State.IDLE);
      }
      mCurrentState = State.IDLE;
    }
  }

  public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}
