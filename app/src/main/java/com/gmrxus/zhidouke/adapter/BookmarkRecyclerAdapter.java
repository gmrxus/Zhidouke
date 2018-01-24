package com.gmrxus.zhidouke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gmrxus.zhidouke.R;
import com.gmrxus.zhidouke.bean.DoubanStory;
import com.gmrxus.zhidouke.bean.Guoke;
import com.gmrxus.zhidouke.bean.ZhihuInfoBean;

import java.util.List;

/**
 * Created by Gmrxus on 2018/1/19.
 */

public class BookmarkRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View
    .OnClickListener {
  public static final int TITILE_ZHIHU = 0;
  public static final int TITLE_DOUBAN = 1;
  public static final int TITLE_GUOKE = 2;

  public static final int MARKER_TYPE_ZHIHU = 3;
  public static final int MARKER_TYPE_DOUBAN = 4;
  public static final int MARKER_TYPE_GUOKE = 5;

  private int markerType;

  private Context mContext;
  private List<ZhihuInfoBean> mZhihuInfoBeans;
  private List<DoubanStory> mDoubanStories;
  private List<Guoke.ResultBean> mGuokeInofBeans;
  private List<Integer> markerTypes;

  public BookmarkRecyclerAdapter(Context context, List<ZhihuInfoBean> zhihuInfoBeans, List<DoubanStory>
      doubanStories, List<Guoke.ResultBean> guokeInofBeans, List<Integer> markerTypes) {
    mContext = context;
    mZhihuInfoBeans = zhihuInfoBeans;
    mDoubanStories = doubanStories;
    mGuokeInofBeans = guokeInofBeans;
    this.markerTypes = markerTypes;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    RecyclerView.ViewHolder holder;
    if (viewType == MARKER_TYPE_ZHIHU || viewType == MARKER_TYPE_DOUBAN || viewType == MARKER_TYPE_GUOKE) {
      View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_item, parent, false);
      view.setOnClickListener(this);
      holder = new BookmarkViewHolder(view);

    } else {
      View view = LayoutInflater.from(mContext).inflate(R.layout.recycle_item_title, parent, false);
      holder = new TitleViewHolder(view);
    }
    return holder;

  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    markerType = markerTypes.get(position);
    int index = -1;
    if (holder instanceof BookmarkViewHolder) {
      BookmarkViewHolder bookmarkViewHolder = (BookmarkViewHolder) holder;
      if (markerType == MARKER_TYPE_ZHIHU) {
        index = position - 1;
        bookmarkViewHolder.tv.setText(mZhihuInfoBeans.get(index).getTitle());
        Glide.with(mContext)
            .load(mZhihuInfoBeans.get(index).getImage())
            .placeholder(R.drawable.placeholder)
            .into(bookmarkViewHolder.iv);

      } else if (markerType == MARKER_TYPE_DOUBAN) {
        index = position - mZhihuInfoBeans.size() - 2;
        bookmarkViewHolder.tv.setText(mDoubanStories.get(index).getTitle());
        Glide.with(mContext)
            .load(mDoubanStories.get(index).getThumbs().size() !=
                0 ?
                mDoubanStories.get(index).getThumbs().get(0).getLarge().getUrl() :
                "https://img3.doubanio.com/lpic/s29441950.jpg")
            .placeholder(R.drawable.placeholder)
            .into(bookmarkViewHolder.iv);

      }
      holder.itemView.setTag(new int[]{markerType, index});
    } else {
      TitleViewHolder titleViewHolder = (TitleViewHolder) holder;
      if (markerType == TITILE_ZHIHU) {
        titleViewHolder.tv.setText(mContext.getString(R.string.zhihu));
      } else if (markerType == TITLE_DOUBAN) {
        titleViewHolder.tv.setText(mContext.getString(R.string.douban));
      } else {
        titleViewHolder.tv.setText(mContext.getString(R.string.guoke));
      }
    }
  }

  @Override
  public int getItemCount() {
    return mZhihuInfoBeans.size() + mDoubanStories.size() + mGuokeInofBeans.size() + 3;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return TITILE_ZHIHU;
    } else if (position == mZhihuInfoBeans.size() + 1) {
      return TITLE_DOUBAN;
    } else if (position == mZhihuInfoBeans.size() + mDoubanStories.size() + 2) {
      return TITLE_GUOKE;
    } else if (position > 0 && position <= mZhihuInfoBeans.size()) {
      return MARKER_TYPE_ZHIHU;
    } else if (position > mZhihuInfoBeans.size() + 1 && position <= mZhihuInfoBeans.size() + mDoubanStories.size() +
        1) {
      return MARKER_TYPE_DOUBAN;
    } else {
      return MARKER_TYPE_GUOKE;
    }
  }

  @Override
  public void onClick(View v) {
    int type = ((int[]) v.getTag())[0];
    int position = ((int[]) v.getTag())[1];
    if (type == MARKER_TYPE_ZHIHU) {
      mListener.itemClick(mZhihuInfoBeans.get(position).getId(), 1);
    } else if (type == MARKER_TYPE_DOUBAN) {
      mListener.itemClick(mDoubanStories.get(position).getId(), 2);
    }
  }

  public interface OnItemClickListener {
    void itemClick(int id, int pageType);
  }

  private OnItemClickListener mListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  class BookmarkViewHolder extends RecyclerView.ViewHolder {

    private final ImageView iv;
    private final TextView tv;

    public BookmarkViewHolder(View itemView) {
      super(itemView);
      iv = itemView.findViewById(R.id.iv_item);
      tv = itemView.findViewById(R.id.tv_item);
    }
  }

  class TitleViewHolder extends RecyclerView.ViewHolder {
    private final TextView tv;

    public TitleViewHolder(View itemView) {
      super(itemView);
      tv = itemView.findViewById(R.id.tv_item_title);
    }
  }
}
