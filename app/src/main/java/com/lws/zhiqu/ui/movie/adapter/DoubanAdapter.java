package com.lws.zhiqu.ui.movie.adapter;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lws.zhiqu.App;
import com.lws.zhiqu.R;
import com.lws.zhiqu.model.bean.DoubanBean;
import com.lws.zhiqu.ui.activity.HomeNewsDetailActivity;
import com.lws.zhiqu.ui.activity.MoviedoubanDetailAtivity;
import com.lws.zhiqu.utils.DoubanUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by song on 2018/2/28.
 */

public class DoubanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private DoubanBean mDoubanBean;

    public DoubanAdapter(Context context) {
        mContext = context;
    }

    public  void replaceAll(DoubanBean doubanBean){

        mDoubanBean = doubanBean;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View rootView = LayoutInflater.from(mContext).inflate(R.layout.movie_douban_recycler_item,parent,false);

            return new DoubanViewHolder(rootView);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       if(holder instanceof DoubanViewHolder){
            DoubanViewHolder doubanViewHolder = (DoubanViewHolder)holder;
            doubanViewHolder.bindItem(position);

        }


    }

    @Override
    public int getItemCount() {
        if (mDoubanBean != null){
            return mDoubanBean.getSubjects().size() ;
        }
        return 0;
    }

    class DoubanViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_movie_item)
        LinearLayout mLinearLayout;
        @BindView(R.id.iv_moive_photo)
        ImageView mImageView;
        @BindView(R.id.tv_movie_title)
        TextView mMovieTitle;
        @BindView(R.id.tv_movie_directors)
        TextView mMoviewDiretors;
        @BindView(R.id.tv_movie_actors)
        TextView mMovieActors;
        @BindView(R.id.tv_movie_genres)
        TextView mMovieGenres;
        @BindView(R.id.tv_movie_rating)
        TextView mMovieRating;

        public DoubanViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        private void bindItem(final int position){
            mMovieTitle.setText(mDoubanBean.getSubjects().get(position).getTitle());
            mMoviewDiretors.setText(mDoubanBean.getSubjects().get(position).getDirectors().get(0).getName());

            mMovieActors.setText(DoubanUtils.getAvatars(mDoubanBean, position));
            mMovieGenres.setText(DoubanUtils.getGenres(mDoubanBean, position));
            mMovieRating.setText(mDoubanBean.getSubjects().get(position).getRating().getAverage()+"");
            Glide.with(mContext).load(mDoubanBean.getSubjects().get(position).getImages().getLarge()).crossFade(300).into(mImageView);
            mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,MoviedoubanDetailAtivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",mDoubanBean.getSubjects().get(position).getId());
                    intent.putExtra("data",bundle);
                    mContext.startActivity(intent);
                }
            });

        }
    }

}
