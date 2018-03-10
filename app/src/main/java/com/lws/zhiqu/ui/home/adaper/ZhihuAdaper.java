package com.lws.zhiqu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
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
import com.lws.zhiqu.model.bean.ZhuListBean;
import com.lws.zhiqu.ui.activity.HomeZhihuDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2018/2/7.
 */

public class ZhihuAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private  final int ITEM_FOOTER = 0;
    private  final int ITEM_NORMAL = 1;
    public   final int LOAD_MORE = 2;
    public   final int LOAD_PULL_TO = 3;
    public   final int LOAD_NONE = 4;
    public   final int LOAD_END = 5;
    private int status = 3;

    private ZhuListBean mZhuListBean;
    private Context mContext;


    public ZhihuAdaper(Context context) {
        this.mContext = context;

    }
    public void addAll(ZhuListBean zhuListBean) {
        mZhuListBean.getStories().addAll(zhuListBean.getStories());
        notifyDataSetChanged();
    }
    public void  replaceAll(ZhuListBean zhuListBean){
        if ( mZhuListBean!=  null) {
            mZhuListBean.getStories() .clear();
        }
        mZhuListBean = zhuListBean;
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

        View rootView = LayoutInflater.from(mContext).inflate(R.layout.home_recycler_item,parent,false);

        return new ZhihuViewHolder(rootView);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof ZhihuViewHolder){
            ZhihuViewHolder zhihuViewHolder = (ZhihuViewHolder) holder;
            zhihuViewHolder.bindItem(position);
        }


    }

    @Override
    public int getItemCount() {
        if (mZhuListBean != null) {

            return mZhuListBean.getStories().size() + 1;
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
    class ZhihuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_title)
        TextView mTitle;
        @BindView(R.id.iv_item_image)
        ImageView mImageView;
        @BindView(R.id.cv_click)
        CardView mCardView;
        public ZhihuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        private void bindItem(final int position){


            mTitle.setText(mZhuListBean.getStories().get(position).getTitle());
            Glide.with(mContext).load(mZhuListBean.getStories().get(position).getImages().get(0)).crossFade().into(mImageView);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTitle.setTextColor(ContextCompat.getColor(App.getContext(),R.color.colorHint));
                    Intent intent = new Intent(mContext,HomeZhihuDetailActivity.class);
                    intent.putExtra("id",mZhuListBean.getStories().get(position).getId()+"");
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
