package com.lws.zhiqu.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.contract.home.NewsContract;
import com.lws.zhiqu.model.bean.NewsBean;
import com.lws.zhiqu.presenter.home.NewsPresenter;
import com.lws.zhiqu.ui.home.adaper.NewsAdapter;


import butterknife.BindView;


/**
 * Created by song on 2018/2/3.
 */

public class NewFragment extends BaseMVPFragment<NewsPresenter>implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private LinearLayoutManager mManager;
    public static NewFragment getInstance(){
        NewFragment newFragment = new NewFragment();
        return  newFragment;

    }
    @Override
    protected void initView(android.view.View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mManager = new LinearLayoutManager(getActivity());
        mNewsAdapter = new NewsAdapter(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mNewsAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                if (newState == mRecyclerView.SCROLL_STATE_IDLE) {
                  int  lastItem = mManager.findLastVisibleItemPosition();
                    if (lastItem +1 == mManager.getItemCount()) {
                        mNewsAdapter.updateLoadStatus(mNewsAdapter.LOAD_PULL_TO);
                        mNewsAdapter.updateLoadStatus(mNewsAdapter.LOAD_MORE);
                        mPresanter.loadMore();

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
    protected NewsPresenter getPresanter() {
        return new NewsPresenter();
    }





    @Override
    public void returnData(NewsBean data, boolean isPege) {
        if (data != null) {
            if (isPege) {
                mNewsAdapter.addAll(data);

            }else {
                mNewsAdapter.replaceAll(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadLatest();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadLatest();

    }
}
