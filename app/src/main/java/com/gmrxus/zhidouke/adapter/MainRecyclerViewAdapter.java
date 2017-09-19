package com.gmrxus.zhidouke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.ZhihuNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/4/24.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View
    .OnClickListener {
  private static final String TAG = "MainRecyclerViewAdapter";
  private List<ZhihuNews> mZhihuNewList = new ArrayList<>();
  private List<ZhihuNews.StoriesBean> mStories = new ArrayList<>();
  private Context mContext;


  enum ItemType {
    content, foot
  }

  public MainRecyclerViewAdapter(Context context, List<ZhihuNews> zhihuNewsList) {
    this.mContext = context;
    this.mZhihuNewList = zhihuNewsList;
    for (ZhihuNews zhihuNews : mZhihuNewList) {
      mStories.addAll(zhihuNews.getStories());
    }
    Log.e(TAG, "MainRecyclerViewAdapter: " + mStories.size());
  }


  public void addContent(List<ZhihuNews> zhihuNewList) {
    mStories.clear();
    for (ZhihuNews zhihuNews : zhihuNewList) {
      mStories.addAll(zhihuNews.getStories());
    }
  }

  public void refresh() {
    mStories.clear();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == ItemType.content.ordinal()) {
      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
      ViewHolderContent viewHolderContent = new ViewHolderContent(rootView);
      rootView.setOnClickListener(this);
      return viewHolderContent;
    } else if (viewType == ItemType.foot.ordinal()) {
      View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_foot, parent, false);
      ViewHolderFoot viewHolderFoot = new ViewHolderFoot(rootView);
      return viewHolderFoot;
    }
    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ViewHolderFoot) {
      ViewHolderFoot viewHolderFoot = (ViewHolderFoot) holder;
    } else if (holder instanceof ViewHolderContent) {
      ViewHolderContent viewHolder = (ViewHolderContent) holder;
      viewHolder.mTv.setText(mStories.get(position).getTitle());
      String images = mStories.get(position).getImages().get(0);
      Glide.with(mContext).load(images).placeholder(R.drawable.placeholder).into(viewHolder.mIv);
      viewHolder.itemView.setTag(position);
    }
  }


  /**
   * 内容
   */
  @Override
  public int getItemViewType(int position) {
    if (position == mStories.size()) {
      return ItemType.foot.ordinal();
    }
    return ItemType.content.ordinal();
  }

  @Override
  public int getItemCount() {
    return mStories.size() + 1;
  }


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
   * 每页脚的日期分割
   */
  class ViewHolderFoot extends RecyclerView.ViewHolder {
    private final TextView footTextView;

    ViewHolderFoot(View itemView) {
      super(itemView);
      footTextView = (TextView) itemView.findViewById(R.id.foot_tv);
    }
  }

  public interface OnItemClickListener {
    void onItemClick(View view, int position);
  }

  private OnItemClickListener mOnItemClickListener;

  public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
    this.mOnItemClickListener = onItemClickListener;
  }

  @Override
  public void onClick(View v) {
    if (mOnItemClickListener != null) {
      mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }
  }
}
