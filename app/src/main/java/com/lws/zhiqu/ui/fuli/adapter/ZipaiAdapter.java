package com.lws.zhiqu.ui.fuli.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lws.zhiqu.R;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.ui.activity.PictureDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2018/3/5.
 */

public class ZipaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ZipaiAdapter(Context context) {
        mContext = context;
    }

    public void addAll(List<String> list) {
        mList.addAll(list);

        notifyDataSetChanged();
    }
    public void  replaceAll(List<String> list){
        mList = list;
        notifyDataSetChanged();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View rootView = LayoutInflater.from(mContext).inflate(R.layout.fuli_girl_recycler_item,parent,false);
            return new ZipaiViewHolder(rootView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


             ZipaiViewHolder zipaiViewHolder= (ZipaiViewHolder) holder;
             zipaiViewHolder.bindItem(position);


    }

    @Override
    public int getItemCount() {
        if ( mList != null) {

            return  mList.size();
        }
        return  0;

    }
    public class ZipaiViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_click)
        CardView mCardView;
        @BindView(R.id.iv_item_image)
        ImageView mImageView;

        public ZipaiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView);
        }

        protected void bindItem(final int p){
            Glide.with(mContext).load(mList.get(p)).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade().into(mImageView);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, PictureDetailActivity.class);

                    intent.putExtra("url",mList.get(p));


                    mContext.startActivity(intent);

                }
            });


        }
    }




}
