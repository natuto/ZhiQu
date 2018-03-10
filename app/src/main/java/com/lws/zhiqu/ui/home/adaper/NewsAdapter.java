package com.lws.zhiqu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lws.zhiqu.App;
import com.lws.zhiqu.R;
import com.lws.zhiqu.model.bean.NewsBean;
import com.lws.zhiqu.ui.activity.HomeNewsDetailActivity;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2018/2/24.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  final int ITEM_FOOTER = 0;
    private  final int ITEM_NORMAL = 1;
    public   final int LOAD_MORE = 2;
    public   final int LOAD_PULL_TO = 3;
    public   final int LOAD_NONE = 4;
    public   final int LOAD_END = 5;
    private int status = 3;
    private Context mContext;
    private NewsBean mNewsBean;
    public NewsAdapter(Context context) {
      this.mContext = context;
    }
    public  void replaceAll(NewsBean newsBean){
        if (newsBean != newsBean) {
        newsBean.getT1348647909107().clear();
        }
        mNewsBean = newsBean;
        notifyDataSetChanged();
    }
    public  void addAll(NewsBean newsBean){
        newsBean.getT1348647909107().addAll(newsBean.getT1348647909107());
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_FOOTER) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_footer,parent,false);
            return new FooterViewHolder(rootView);
        }else {

            View rootView = LayoutInflater.from(mContext).inflate(R.layout.home_recycler_item,parent,false);

            return new NesViewHolder(rootView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof NesViewHolder){
            NesViewHolder nesViewHolder = (NesViewHolder) holder;
            nesViewHolder.bindItem(position);
        }


    }

    @Override
    public int getItemCount() {
        if (mNewsBean != null){
            return mNewsBean.getT1348647909107().size()+1;
        }
        return 0;
    }
    @Override
    public int getItemViewType(int position) {
        int type = ITEM_NORMAL;
        if (position +1 == getItemCount()) {
            type = ITEM_FOOTER;
        }
        return type;
    }
    class NesViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_title)
        TextView mTitle;
        @BindView(R.id.iv_item_image)
        ImageView mImageView;
        @BindView(R.id.cv_click)
        CardView mCardView;
        @BindView(R.id.tv_item_who)
        TextView mSource;
        @BindView(R.id.tv_item_time)
        TextView mTime;
        public NesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bindItem(final int position){
            mTime.setText(mNewsBean.getT1348647909107().get(position).getPtime());
            mSource.setText(mNewsBean.getT1348647909107().get(position).getSource());
            mTitle.setText(mNewsBean.getT1348647909107().get(position).getTitle());
            Glide.with(mContext).load(mNewsBean.getT1348647909107().get(position).getImgsrc()).crossFade().into(mImageView);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTitle.setTextColor(ContextCompat.getColor(App.getContext(), R.color.colorHint));
                    Intent intent = new Intent(mContext,HomeNewsDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("image",mNewsBean.getT1348647909107().get(position).getImgsrc());
                    bundle.putString("url",mNewsBean.getT1348647909107().get(position).getUrl());
                    intent.putExtra("data",bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }
    class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_load_prompt)
        TextView tv_load_prompt;
        @BindView(R.id.progress)
        ProgressBar progress;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        private void bindItem() {
            switch (status) {
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    tv_load_prompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }
    }
    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}
