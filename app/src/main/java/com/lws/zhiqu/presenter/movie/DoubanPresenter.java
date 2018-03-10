package com.lws.zhiqu.presenter.movie;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.movie.MovieContract;
import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.model.bean.DoubanBean;
import com.lws.zhiqu.model.movie.DoubanModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by song on 2018/2/28.
 */

public class DoubanPresenter extends BasePresenter<MovieContract.View>implements MovieContract.Presenter {
    private DoubanModel mDoubanModel;
    public DoubanPresenter() {
        mDoubanModel = new DoubanModel();
    }


    @Override
    public void loadLatest() {
        mDoubanModel.getLatestData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DoubanBean>() {
            @Override
            public void accept(DoubanBean doubanBean) throws Exception {

                view.returnData(doubanBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });

    }

    @Override
    public void loadDetail(String id) {
        mDoubanModel.getDetailData(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DouDetailBean>() {
            @Override
            public void accept(DouDetailBean doubanBean) throws Exception {

                view.returnData(doubanBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });

    }
}
