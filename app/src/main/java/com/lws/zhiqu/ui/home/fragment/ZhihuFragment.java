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
import com.lws.zhiqu.contract.home.ZhihuContract;
import com.lws.zhiqu.model.bean.ZhuListBean;
import com.lws.zhiqu.presenter.home.ZhihuPresenter;
import com.lws.zhiqu.ui.home.adaper.ZhihuAdaper;

import butterknife.BindView;

/**
 * Created by song on 2018/2/3.
 */

public class ZhihuFragment extends BaseMVPFragment<ZhihuPresenter> implements ZhihuContract.View<ZhuListBean>,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ZhihuAdaper mZhiAdaper  ;
    private String mTime;
    private LinearLayoutManager mManager;



    public static ZhihuFragment getInstance(){
        ZhihuFragment zhiHuFragment = new ZhihuFragment();
        return  zhiHuFragment;

    }
    @Override
   protected void initView(View view) {

        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mManager = new LinearLayoutManager(getActivity());
        mZhiAdaper = new ZhihuAdaper(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mZhiAdaper);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == mRecyclerView.SCROLL_STATE_IDLE) {
                   int lastItem = mManager.findLastVisibleItemPosition();
                    if (lastItem +1 == mManager.getItemCount()) {
                        mZhiAdaper.updateLoadStatus(mZhiAdaper.LOAD_PULL_TO);
                        mZhiAdaper.updateLoadStatus(mZhiAdaper.LOAD_MORE);
                        mPresanter.loadMore(mTime);

                    }

                }
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected ZhihuPresenter getPresanter() {
        return new ZhihuPresenter();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadData();
    }



    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        mPresanter.loadData();

    }

    @Override
    public void  returnData(ZhuListBean zhuListBean,boolean isPege) {
        mTime = zhuListBean.getDate();
        if (mTime.equals("")) {
            mZhiAdaper.updateLoadStatus(mZhiAdaper.LOAD_NONE);
        }else {
           if (!isPege) {
            mZhiAdaper.replaceAll(zhuListBean);

            mSwipeRefreshLayout.setRefreshing(false);
            }else {
            mZhiAdaper.addAll(zhuListBean);
        }
        }
    }

    @Override
    public void showError() {

      Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();

    }
}
