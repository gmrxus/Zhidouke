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
import com.gmrxus.zhidouke.bean.Guoke;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gmrxus on 2018/1/15.
 */

public class GuokeRecyclerViewAdapter extends RecyclerView.Adapter implements View.OnClickListener {

  private final Context mContext;
  private final LayoutInflater mInflater;
  private List<Guoke.ResultBean> mList;

  public GuokeRecyclerViewAdapter(Context context, List<Guoke.ResultBean> guokeListBean) {
    mContext = context;
    mInflater = LayoutInflater.from(mContext);
    mList = new ArrayList<>();
    mList.addAll(guokeListBean);
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View rootView = mInflater.inflate(R.layout.item_recycler_guoke, parent, false);
    GuokeViewHolder guokeViewHolder = new GuokeViewHolder(rootView);
    rootView.setOnClickListener(this);
    return guokeViewHolder;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    GuokeViewHolder viewHolder = (GuokeViewHolder) holder;
    Guoke.ResultBean resultBean = mList.get(position);
    viewHolder.tvTitle.setText(resultBean.getTitle());
    viewHolder.tvContent.setText(resultBean.getSummary());
    Glide.with(mContext)
        .load(resultBean.getHeadline_img_tb())
        .asBitmap()
        .error(R.drawable.placeholder)
        .placeholder(R.drawable.placeholder)
        .into(viewHolder.iv);
    Glide.with(mContext)
        .load(resultBean.getSource_data().getImage())
        .asBitmap()
        .error(R.drawable.ic_author)
        .placeholder(R.drawable.ic_author)
        .into(viewHolder.ivAuthor);
    viewHolder.tvAuthor.setText(resultBean.getAuthor());
    viewHolder.tvDiscuess.setText(resultBean.getReplies_count() + "");
    viewHolder.tvZan.setText(resultBean.getLikings_count() + "");
    viewHolder.itemView.setTag(position);
  }

  @Override
  public int getItemCount() {
    return mList.size();
  }

  @Override
  public void onClick(View v) {
    mListener.click(v, (Integer) v.getTag());
  }

  private OnItemClickListener mListener;

  public void setOnItemClickListener(OnItemClickListener listener) {
    mListener = listener;
  }

  public interface OnItemClickListener {
    void click(View view, int position);
  }

  class GuokeViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvContent;
    private final TextView tvAuthor;
    private final ImageView iv;
    private final TextView tvDiscuess;
    private final TextView tvZan;
    private final ImageView ivShares;
    private final ImageView ivAuthor;

    public GuokeViewHolder(View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tv_item_guoke_title);
      tvContent = itemView.findViewById(R.id.tv_item_guoke_content);
      iv = itemView.findViewById(R.id.iv_item_guoke_image);
      ivAuthor = itemView.findViewById(R.id.iv_item_guoke_author);
      tvAuthor = itemView.findViewById(R.id.tv_item_guoke_author);
      tvDiscuess = itemView.findViewById(R.id.tv_item_guoke_discuss);
      tvZan = itemView.findViewById(R.id.tv_item_guoke_zan);
      ivShares = itemView.findViewById(R.id.iv_item_guoke_shares);
    }
  }
}
