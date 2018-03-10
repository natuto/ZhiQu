package com.lws.zhiqu.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by song on 2018/2/4.
 */

public class BaseFragmentAdaper extends FragmentPagerAdapter {
    public   FragmentManager fm;
    public List<BaseMVPFragment> mFragments;
    public   String[] mTitle;

    public BaseFragmentAdaper(FragmentManager fm, List<BaseMVPFragment> fragments , String[] tilte) {
        super(fm);
        this.fm = fm;
        this.mFragments = fragments;
        this.mTitle = tilte;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
