package com.lws.zhiqu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by song on 2018/2/3.
 */

public abstract class BaseMVPFragment<P extends BasePresenter> extends SupportFragment {
    public P mPresanter;
    public Unbinder mUnbinder;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresanter== null) {
            mPresanter = getPresanter();
            mPresanter.attchView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(),container,false);
        mUnbinder = ButterKnife.bind(this,rootView);
        this.initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mUnbinder!=null) {
            mUnbinder.unbind();
        }
        if (mPresanter != null) {
            mPresanter.detchView();
        }
    }

    protected abstract void initView(View view);

    protected abstract int getLayoutId();

    protected abstract P getPresanter();
}
