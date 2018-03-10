package com.lws.zhiqu.ui.movie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseMVPFragment;
import com.lws.zhiqu.contract.movie.MovieContract;
import com.lws.zhiqu.model.bean.DoubanBean;
import com.lws.zhiqu.presenter.movie.DoubanPresenter;
import com.lws.zhiqu.ui.home.adaper.WeixinAdaper;
import com.lws.zhiqu.ui.movie.adapter.DoubanAdapter;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by song on 2018/2/28.
 */

public class DoubanFragment extends BaseMVPFragment<DoubanPresenter>  implements MovieContract.View<DoubanBean>,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DoubanAdapter mDoubanAdapter;

    public static DoubanFragment getInstance(){
        DoubanFragment doubanFragment = new DoubanFragment();
        return  doubanFragment;
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(),"网络出错",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void returnData(DoubanBean doubanBean) {


            mDoubanAdapter.replaceAll(doubanBean );
            mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.refresh_progress1,R.color.refresh_progress2,R.color.refresh_progress3);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mDoubanAdapter = new DoubanAdapter(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mDoubanAdapter);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected DoubanPresenter getPresanter() {
        return new DoubanPresenter();
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
