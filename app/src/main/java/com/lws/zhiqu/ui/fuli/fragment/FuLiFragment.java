package com.lws.zhiqu.ui.fuli.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseFragment;
import com.lws.zhiqu.base.BaseFragmentAdaper;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.ui.gank.fragment.AndroidFragment;
import com.lws.zhiqu.ui.gank.fragment.IosFragment;
import com.lws.zhiqu.ui.gank.fragment.ResourceFragment;
import com.lws.zhiqu.ui.gank.fragment.RestFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by song on 2018/1/31.
 */

public class FuLiFragment extends BaseFragment {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.tv_title)
    TextView setTitle;
    private List<BaseMVPFragment> mFragments;
    private String[] mTitle = {"gank","煎蛋","自拍"};

    public static FuLiFragment newInstance(){
        FuLiFragment fiLiFragment = new FuLiFragment();
        return  fiLiFragment;
    }
    @Override
    public void initView() {
        setTitle.setText("福利");
        mFragments = new ArrayList<>();
        mFragments.add(GirlFragment.getInstance());
        mFragments.add(JiandanFragment.getInstance());
        mFragments.add(ZipaiFragment.getInstance());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new BaseFragmentAdaper(getChildFragmentManager(),mFragments,mTitle));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }
}
