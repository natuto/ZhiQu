package com.lws.zhiqu.ui.fuli.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.contract.fuli.FuliContract;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.presenter.fuli.GirlPresenter;
import com.lws.zhiqu.ui.fuli.adapter.GirlAdapter;

import butterknife.BindView;

/**
 * Created by song on 2018/3/4.
 */

public class GirlFragment extends BaseMVPFragment <GirlPresenter>implements FuliContract.View<GirlBean> ,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GirlBean mGirlBean;
    private GirlAdapter mGirlAdapter;
    private GridLayoutManager mGridLayoutManager;
    public  static GirlFragment getInstance(){
        GirlFragment girlFragment = new GirlFragment();
        return girlFragment;
    }
    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mGridLayoutManager = new GridLayoutManager(getActivity(),2);
        mGirlAdapter = new GirlAdapter(getActivity());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mGirlAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
                if (newState == mRecyclerView.SCROLL_STATE_IDLE) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    int  lastItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();

                    if (lastItem +1 == recyclerView.getLayoutManager().getItemCount()) {
                        mPresanter.loadMore();

                    }

                }
            }
        });


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler ;
    }

    @Override
    protected GirlPresenter getPresanter() {
        return new GirlPresenter();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
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
    @Override
    public void returnData(GirlBean data, boolean isPage) {
        if (data != null) {
            if (isPage) {
                mGirlAdapter.addAll(data);

            }else {
                mGirlAdapter.replaceAll(data);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }

    }
}
