package com.lws.zhiqu.ui.home.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseFragment;
import com.lws.zhiqu.base.BaseFragmentAdaper;
import com.lws.zhiqu.base.BaseMVPFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by song on 2018/1/31.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    private List<BaseMVPFragment> mFragments;
    private String[] mTitle= {"知乎日报","新闻资讯","微信精选"};


    public static HomeFragment newInstance(){
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }
    @Override
    public void initView() {
        mFragments = new ArrayList<>();
        mFragments.add(ZhihuFragment.getInstance());
        mFragments.add(NewFragment.getInstance());
        mFragments.add(WeixinFragment.getInstance());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new BaseFragmentAdaper(getChildFragmentManager(),mFragments,mTitle));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
