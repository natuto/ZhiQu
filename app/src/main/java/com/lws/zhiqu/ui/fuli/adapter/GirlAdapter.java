package com.lws.zhiqu.ui.fuli.adapter;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lws.zhiqu.App;
import com.lws.zhiqu.R;
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.ui.activity.GankDetailActivity;
import com.lws.zhiqu.ui.activity.PictureDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lws.zhiqu.ui.home.adaper.WeixinAdaper.LOAD_END;
import static com.lws.zhiqu.ui.home.adaper.WeixinAdaper.LOAD_NONE;

/**
 * Created by song on 2018/3/5.
 */

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private GirlBean mGirlBean;

    public GirlAdapter(Context context) {
        mContext = context;
    }

    public void addAll(GirlBean girlBean) {
        mGirlBean.getResults().addAll(girlBean.getResults());

        notifyDataSetChanged();
    }
    public void  replaceAll(GirlBean girlBean){
        mGirlBean = girlBean;
        notifyDataSetChanged();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View rootView = LayoutInflater.from(mContext).inflate(R.layout.fuli_girl_recycler_item,parent,false);
            return new GirlViewHolder(rootView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


            GirlViewHolder girlViewHolder = (GirlViewHolder) holder;
            girlViewHolder.bindItem(position);


    }

    @Override
    public int getItemCount() {
        if ( mGirlBean!= null) {

            return  mGirlBean.getResults().size();
        }
        return  0;

    }
    public class GirlViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_click)
        CardView mCardView;
        @BindView(R.id.iv_item_image)
        ImageView mImageView;

        public GirlViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        protected void bindItem(final int p){
            Glide.with(mContext).load(mGirlBean.getResults().get(p).getUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade().into(mImageView);
            mCardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   Intent intent = new Intent(mContext, PictureDetailActivity.class);

                   intent.putExtra("url",mGirlBean.getResults().get(p).getUrl());


                   mContext.startActivity(intent);

               }
           });

        }
    }




}
