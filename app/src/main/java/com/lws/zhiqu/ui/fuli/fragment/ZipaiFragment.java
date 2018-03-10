package com.lws.zhiqu.ui.fuli.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.contract.fuli.FuliContract;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.presenter.fuli.JiandanPresenter;
import com.lws.zhiqu.presenter.fuli.ZipaiPresenter;
import com.lws.zhiqu.ui.fuli.adapter.JiandanAdapter;
import com.lws.zhiqu.ui.fuli.adapter.ZipaiAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by song on 2018/3/4.
 */

public class ZipaiFragment extends BaseMVPFragment <ZipaiPresenter>implements FuliContract.View<List<String>> ,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ZipaiAdapter mZipaiAdapter;
    private GridLayoutManager mGridLayoutManager;
    public  static ZipaiFragment getInstance(){
        ZipaiFragment zipaiFragment = new ZipaiFragment();
        return zipaiFragment;
    }
    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mGridLayoutManager = new GridLayoutManager(getActivity(),2);
        mZipaiAdapter= new ZipaiAdapter(getActivity());
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mZipaiAdapter);
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
    protected ZipaiPresenter getPresanter() {
        return new ZipaiPresenter();
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
    public void returnData(List<String> data, boolean isPage) {
        if (isPage) {
            mZipaiAdapter.addAll(data);
        }else {
            mZipaiAdapter.replaceAll(data );
            mSwipeRefreshLayout.setRefreshing(false);
        }

    }
}
