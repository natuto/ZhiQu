package com.lws.zhiqu.ui.movie.fragment;

import android.widget.TextView;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by song on 2018/1/31.
 */

public class MovieFragment extends BaseFragment {



    public static MovieFragment newInstance(){
        MovieFragment movieFragment = new MovieFragment();
        return  movieFragment;
    }
    @Override
    public void initView() {
        ;
      loadRootFragment(R.id.container, DoubanFragment.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_movie;
    }
}
