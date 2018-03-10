package com.lws.zhiqu.ui.gank.adaper;

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
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.ui.activity.GankDetailActivity;
import com.lws.zhiqu.ui.activity.HomeWeixinDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * Created by song on 2018/2/26.
 */

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  final int ITEM_FOOTER = 0;
    private  final int ITEM_NORMAL = 1;
    public   final int LOAD_MORE = 2;
    public   final int LOAD_PULL_TO = 3;
    public   final int LOAD_NONE = 4;
    public   final int LOAD_END = 5;
    private int status = 3;
    private Context mContext;
    private GankBean mGankBean;

    public GankAdapter(Context context) {
        mContext = context;
    }

    public void addAll(GankBean gankBean) {
        mGankBean.getResults().addAll(gankBean.getResults());
        notifyDataSetChanged();
    }
    public void  replaceAll(GankBean gankBean){
        if ( mGankBean!=  null) {
           mGankBean.getResults().clear();
        }
        mGankBean = gankBean;
        notifyDataSetChanged();

    }
    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_FOOTER) {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_footer,parent,false);
            return new FooterViewHolder(rootView);
        }else {
            View rootView = LayoutInflater.from(mContext).inflate(R.layout.gank_recycler_item,parent,false);
            return new GankViewHolder(rootView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof GankViewHolder){
            GankViewHolder gankViewHolder = (GankViewHolder) holder;
            gankViewHolder.bindItem(position);
        }


    }

    @Override
    public int getItemCount() {
        if (mGankBean != null) {

            return  mGankBean.getResults().size()+ 1;
        }
        return  0;

    }

    @Override
    public int getItemViewType(int position) {
        int type = ITEM_NORMAL;
        if (position +1 == getItemCount()) {
            type = ITEM_FOOTER;
        }
        return type;
    }
    class GankViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_name)
        TextView mName;
        @BindView(R.id.cv_click)
        CardView mCardView;
        @BindView(R.id.tv_type)
        TextView mType;
        @BindView(R.id.tv_time)
        TextView mTime;
        public GankViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        protected void bindItem(final int position){
            mType.setText(mGankBean.getResults().get(position).getType());
            mTitle.setText(mGankBean.getResults().get(position).getDesc());
            mName.setText(mGankBean.getResults().get(position).getWho());
            mTime.setText(mGankBean.getResults().get(position).getCreatedAt().substring(0, 10));
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTitle.setTextColor(ContextCompat.getColor(App.getContext(),R.color.colorHint));
                    Intent intent = new Intent(mContext, GankDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url",mGankBean.getResults().get(position).getUrl());
                    bundle.putString("type",mGankBean.getResults().get(position).getType());
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
}
