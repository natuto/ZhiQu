package com.lws.zhiqu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseThemeActivity;
import com.lws.zhiqu.contract.movie.MovieContract;
import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.presenter.movie.DoubanPresenter;
import com.lws.zhiqu.utils.DoubanUtils;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by song on 2018/3/1.
 */

public class MoviedoubanDetailAtivity extends BaseThemeActivity implements MovieContract.View<DouDetailBean>{
    @BindView(R.id.iv_header_bg)
    ImageView mHeaderBackGround;
    @BindView(R.id.image_movie_photo)
    ImageView mMoviePhoto;
    @BindView(R.id.tv_movie_rating_number)
    TextView mMoviewRating;
    @BindView(R.id.tv_movie_directors)
    TextView mMovieDirectors;
    @BindView(R.id.tv_movie_casts)
    TextView mMovieCasts;
    @BindView(R.id.tv_movie_genres)
    TextView mMovieGenres;
    @BindView(R.id.tv_movie_date)
    TextView mMovieData;
    @BindView(R.id.tv_moive_summary)
    TextView mMovieSummary;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private DoubanPresenter mDoubanPresenter;
    private DouDetailBean mDouDetailBean;
    @Override
    protected int getlayoutId() {
        return R.layout.activity_movie_detail;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        mDoubanPresenter = new DoubanPresenter();
        mDoubanPresenter.attchView(this);
        Bundle bundle = getIntent().getBundleExtra("data");
        mDoubanPresenter.loadDetail(bundle.getString("id"));

    }

    @Override
    public void showError() {

    }
    private void initData(){
        Glide.with(this)
                .load(mDouDetailBean.getImages().getLarge())
                .crossFade(300).bitmapTransform(new BlurTransformation(this,23, 4))
                .into(mHeaderBackGround);
        Glide.with(this).load(mDouDetailBean.getImages().getLarge()).asBitmap().into(mMoviePhoto);
        mToolbar.setTitle(mDouDetailBean.getTitle());
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mMoviewRating.setText(mDouDetailBean.getRating().getAverage()+"");
        mMovieDirectors.setText(mDouDetailBean.getDirectors().get(0).getName());
        mMovieCasts.setText(DoubanUtils.getCasts(mDouDetailBean));
        mMovieGenres.setText(DoubanUtils.getDetailGenres(mDouDetailBean));
        mMovieData.setText(mDouDetailBean.getYear());
        mMovieSummary.setText(mDouDetailBean.getSummary());

    }

    @Override
    public void returnData(DouDetailBean doubanBean) {
        if (doubanBean != null) {
            mDouDetailBean = doubanBean;
            initData();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDoubanPresenter != null) {
            mDoubanPresenter.detchView();
        }
    }
}
