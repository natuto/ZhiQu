package com.lws.zhiqu.ui.fuli.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lws.zhiqu.R;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.ui.activity.PictureDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2018/3/5.
 */

public class JiandanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private JiandanBean mJiandanBean;

    public JiandanAdapter(Context context) {
        mContext = context;
    }

    public void addAll(JiandanBean jiandanBean) {
        mJiandanBean.getComments().addAll(jiandanBean.getComments());

        notifyDataSetChanged();
    }
    public void  replaceAll(JiandanBean jiandanBean){
        mJiandanBean = jiandanBean;
        notifyDataSetChanged();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View rootView = LayoutInflater.from(mContext).inflate(R.layout.fuli_girl_recycler_item,parent,false);
            return new JiandanViewHolder(rootView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


            JiandanViewHolder jiandanViewHolder = (JiandanViewHolder) holder;
            jiandanViewHolder.bindItem(position);


    }

    @Override
    public int getItemCount() {
        if ( mJiandanBean!= null) {

            return  mJiandanBean.getComments().size();
        }
        return  0;

    }
    public class JiandanViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cv_click)
        CardView mCardView;
        @BindView(R.id.iv_item_image)
        ImageView mImageView;

        public JiandanViewHolder(View itemView) {
            super(itemView);
           ButterKnife.bind(this ,itemView);
        }

        protected void bindItem(final int p){
            Glide.with(mContext).load(mJiandanBean.getComments().get(p).getPics().get(0)).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().crossFade().into(mImageView);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, PictureDetailActivity.class);

                    intent.putExtra("url",mJiandanBean.getComments().get(p).getPics().get(0));


                    mContext.startActivity(intent);

                }
            });

        }
    }




}
