package com.lws.zhiqu.ui.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.gank.GankContract;
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.presenter.gank.GankPresenter;
import com.lws.zhiqu.ui.gank.adaper.GankAdapter;
import com.lws.zhiqu.ui.home.fragment.HomeFragment;

import butterknife.BindView;

/**
 * Created by song on 2018/2/25.
 */

public class ResourceFragment extends BaseMVPFragment<GankPresenter>implements GankContract.View ,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GankAdapter mGankAdapter;
    private LinearLayoutManager mLayoutManager;
    public static ResourceFragment newInstance(){
        ResourceFragment resourceFragment = new ResourceFragment();
        return resourceFragment;
    }
    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mGankAdapter = new GankAdapter(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mGankAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                if (newState == mRecyclerView.SCROLL_STATE_IDLE) {
                    int  lastItem = mLayoutManager.findLastVisibleItemPosition();
                    if (lastItem +1 == mLayoutManager.getItemCount()) {
                        mGankAdapter.updateLoadStatus(mGankAdapter.LOAD_PULL_TO);
                        mGankAdapter.updateLoadStatus(mGankAdapter.LOAD_MORE);
                        mPresanter.loadMore("拓展资源");

                    }

                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected GankPresenter getPresanter() {
        return new GankPresenter();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadLatest("拓展资源" );
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadLatest("拓展资源");
    }


    @Override
    public void returnData(GankBean gankBean, boolean isPage) {
        if (gankBean.getResults()!=null) {
            if (!isPage) {
                mGankAdapter.replaceAll(gankBean);
                mSwipeRefreshLayout.setRefreshing(false);
            }else {
                mGankAdapter.addAll(gankBean);

            }
        }

    }
}
