package com.lws.zhiqu.ui.gank.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

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

public class GankFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tv_title)
    TextView setTitle;
    private List<BaseMVPFragment> mFragments;
    private String[] mTitle= {"Android","休息视频","拓展资源","iOS"};

    public static GankFragment newInstance(){
        GankFragment ganHuoFragment = new GankFragment();
        return  ganHuoFragment;
    }
    @Override
    public void initView() {
        setTitle.setText("干货");
        mFragments = new ArrayList<>();
        mFragments.add(AndroidFragment.getInstance());
        mFragments.add(RestFragment.getInstance());
        mFragments.add(ResourceFragment.newInstance());
        mFragments.add(IosFragment.getInstance());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new BaseFragmentAdaper(getChildFragmentManager(),mFragments,mTitle));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

}
