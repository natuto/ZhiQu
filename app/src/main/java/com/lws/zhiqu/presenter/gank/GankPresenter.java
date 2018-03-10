package com.lws.zhiqu.presenter.gank;

import com.lws.zhiqu.api.GankApi;
import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.gank.GankContract;
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.model.gank.GankModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by song on 2018/2/26.
 */

public class GankPresenter extends BasePresenter<GankContract.View>implements GankContract.Presenter {
    private GankModel mGankModel;
    private  int mPageSize = 20;
    private  int mPage;
    public GankPresenter() {
        mGankModel = new GankModel();
    }

    @Override
    public void loadLatest(String type) {
        mPage = 1;
        mGankModel.getLatestData(type, mPageSize ,mPage).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GankBean>() {
            @Override
            public void accept(GankBean gankBean) throws Exception {
                view.returnData(gankBean , false);


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });

    }

    @Override
    public void loadMore(String type) {
        mPage++;
        mGankModel.getLatestData(type, mPageSize, mPage).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<GankBean>() {
            @Override
            public void accept(GankBean gankBean) throws Exception {
                view.returnData(gankBean , true);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });


    }
}
