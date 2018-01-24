package com.gmrxus.zhidouke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.Douban;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2017/9/20.
 */

public class DoubanRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View
    .OnClickListener {
  private Context mContext;
  private List<Douban.PostsBean> mPostsBeanList;
  private static final int CONTENT = 0;
  private static final int DATE = 2;
  private static final int FOOT = 1;
  private LayoutInflater mInflater;
  private String mLastItemDate;
  private int mAddiation;


  public DoubanRecyclerViewAdapter(Context context, List<Douban> doubanList) {
    mPostsBeanList = new ArrayList<>();
    mContext = context;
    mInflater = LayoutInflater.from(mContext);
//    for (Douban douban : mDoubanList) {
//      mPostsBeanList.addAll(douban.getPosts());
//      mLastItemDate = douban.getDate();
//    }

    addContents(doubanList);
  }

  public String lastDate() {
    return mLastItemDate;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == CONTENT) {
      View rootView = mInflater.inflate(R.layout.item_recycler_douban, parent, false);
      rootView.setOnClickListener(this);
      DoubanViewHolder doubanViewHolder = new DoubanViewHolder(rootView);
      return doubanViewHolder;
    } else if (viewType == DATE) {
      View rootView = mInflater.inflate(R.layout.recycle_item_title, parent, false);
      ViewHolderTitle holderTitle = new ViewHolderTitle(rootView);
      return holderTitle;
    } else {
      View rootView = mInflater.inflate(R.layout.recycle_item_foot, parent, false);
      ViewHolderFoot viewHolderFoot = new ViewHolderFoot(rootView);
      return viewHolderFoot;
    }
  }

  @Override
  public int getItemViewType(int position) {
    if (position == mPostsBeanList.size()) {
      return FOOT;
    } else if (position == 0 || null == mPostsBeanList.get(position).getUrl()) {
      return DATE;
    } else {
      return CONTENT;
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof DoubanViewHolder) {
      DoubanViewHolder viewHolder = (DoubanViewHolder) holder;
      viewHolder.itemView.setTag(position);
      Douban.PostsBean postsBean = mPostsBeanList.get(position);
      String column = postsBean.getColumn();
      if (TextUtils.isEmpty(column)) {
        viewHolder.tvColumn.setVisibility(View.GONE);
      } else {
        viewHolder.tvColumn.setVisibility(View.VISIBLE);
        viewHolder.tvColumn.setText(column);
      }
      viewHolder.tvTitle.setText(postsBean.getTitle());
      viewHolder.tvAbstract.setText(postsBean.getAbstractX());
      List<Douban.PostsBean.ThumbsBean> thumbs = postsBean.getThumbs();
      viewHolder.iv.setVisibility(thumbs.size() == 0 ? View.GONE : View.VISIBLE);
      if (thumbs.size() != 0) {
        String url = thumbs.get(0).getLarge().getUrl();
        Glide.with(mContext)
            .load(url)
            .asBitmap()
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(viewHolder.iv);
      }
    } else if (holder instanceof ViewHolderTitle) {
      ViewHolderTitle viewHolder = (ViewHolderTitle) holder;
      viewHolder.tvTitle.setText(mPostsBeanList.get(position).getPublished_time());
    }
  }


  @Override
  public int getItemCount() {
    return mPostsBeanList.size() + 1;
  }

  public void addContents(List<Douban> doubanList) {
    mPostsBeanList.clear();
    for (Douban douban : doubanList) {
      Douban.PostsBean postsBean = new Douban.PostsBean();
      postsBean.setPublished_time(douban.getDate());
      mPostsBeanList.add(postsBean);
      mPostsBeanList.addAll(douban.getPosts());
      mLastItemDate = douban.getDate();
    }
  }

  @Override
  public void onClick(View v) {
    if (mListener != null) {
      int position = (int) v.getTag();
      mListener.click(position, mPostsBeanList.get(position));
    }
  }

  private OnItemClickListener mListener;

  public interface OnItemClickListener {
    void click(int position, Douban.PostsBean bean);
  }

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public class DoubanViewHolder extends RecyclerView.ViewHolder {
    private TextView tvColumn;
    private TextView tvTitle;
    private TextView tvAbstract;
    private ImageView iv;

    public DoubanViewHolder(View itemView) {
      super(itemView);
      tvColumn = itemView.findViewById(R.id.tv_item_douban_column);
      tvTitle = itemView.findViewById(R.id.tv_item_douban_title);
      tvAbstract = itemView.findViewById(R.id.tv_item_douban_abstract);
      iv = itemView.findViewById(R.id.iv_item_douban_image);
    }
  }

  /**
   * 页面的时间item
   */
  class ViewHolderTitle extends RecyclerView.ViewHolder {
    private final TextView tvTitle;

    public ViewHolderTitle(View itemView) {
      super(itemView);
      tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
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
