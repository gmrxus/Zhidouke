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

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHoderContent> {
  private static final String TAG = "MainRecyclerViewAdapter";
  private List<ZhihuNews> mZhihuNewList = new ArrayList<>();
  private List<ZhihuNews.StoriesBean> mStories = new ArrayList<>();
  private Context mContext;


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
  public ViewHoderContent onCreateViewHolder(ViewGroup parent, int viewType) {
    View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
    ViewHoderContent viewHoderContent = new ViewHoderContent(rootView);
    return viewHoderContent;
  }

  @Override
  public void onBindViewHolder(ViewHoderContent holder, int position) {


    holder.mTv.setText(mStories.get(position).getTitle());
    String images = mStories.get(position).getImages().get(0);
    Glide.with(mContext).load(images).placeholder(R.drawable.placeholder).centerCrop().into(holder.mIv);

  }


  @Override
  public int getItemCount() {
    return mStories.size();
  }


  /**
   * 内容
   */
  public class ViewHoderContent extends RecyclerView.ViewHolder {
    private final TextView mTv;
    private final ImageView mIv;

    public ViewHoderContent(View itemView) {
      super(itemView);
      mTv = (TextView) itemView.findViewById(R.id.tv_item);
      mIv = (ImageView) itemView.findViewById(R.id.iv_item);
    }
  }


}
