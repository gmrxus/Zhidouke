package com.gmrxus.zhidouke.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmrxus.zhidouke.R;

/**
 * Created by Gmrxus on 2018/1/5.
 */

public class ZhihuRecyclerViewAdapter extends RecyclerView.Adapter {
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  /**
   * 时间item
   */
  class ViewHolderTitle extends RecyclerView.ViewHolder {

    private TextView tvTitle;

    public ViewHolderTitle(View itemView) {
      super(itemView);
      tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
    }
  }

  /**
   * 内容item
   */
  class ViewHolderContent extends RecyclerView.ViewHolder {
    private final TextView mTv;
    private final ImageView mIv;

    ViewHolderContent(View itemView) {
      super(itemView);
      mTv = (TextView) itemView.findViewById(R.id.tv_item);
      mIv = (ImageView) itemView.findViewById(R.id.iv_item);
    }
  }

  /**
   * 页脚加载时的item
   */
  class ViewHolderFoot extends RecyclerView.ViewHolder {
    private final TextView footTextView;

    ViewHolderFoot(View itemView) {
      super(itemView);
      footTextView = (TextView) itemView.findViewById(R.id.foot_tv);
    }
  }


}
