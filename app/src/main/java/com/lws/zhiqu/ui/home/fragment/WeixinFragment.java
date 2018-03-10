package com.lws.zhiqu.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.contract.home.WinxinContract;
import com.lws.zhiqu.model.bean.WeiXinBean;
import com.lws.zhiqu.presenter.home.WeixinPresenter;
import com.lws.zhiqu.ui.home.adaper.WeixinAdaper;

import butterknife.BindView;

/**
 * Created by song on 2018/2/3.
 */

public class WeixinFragment extends BaseMVPFragment<WeixinPresenter> implements WinxinContract.View<WeiXinBean>,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private WeixinAdaper mWeixinAdaper;
    private LinearLayoutManager mManager;

    public static WeixinFragment getInstance(){
        WeixinFragment weiXinFragment = new WeixinFragment();
        return  weiXinFragment;
    }


    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mManager = new LinearLayoutManager(getActivity());
        mWeixinAdaper = new WeixinAdaper(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mWeixinAdaper);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == mRecyclerView.SCROLL_STATE_IDLE) {
                    int lastItem = mManager.findLastVisibleItemPosition();
                    if (lastItem +1 == mManager.getItemCount()) {
                        mWeixinAdaper.updateLoadStatus(mWeixinAdaper.LOAD_PULL_TO);
                        mWeixinAdaper.updateLoadStatus(mWeixinAdaper.LOAD_MORE);
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
    protected WeixinPresenter getPresanter() {
        return new WeixinPresenter();
    }



    @Override
    public void returnData(WeiXinBean data, boolean isPege) {
        if (data != null) {
            if (isPege) {
                mWeixinAdaper.addAll(data);

            }else {
                mWeixinAdaper.replaceAll(data);
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
