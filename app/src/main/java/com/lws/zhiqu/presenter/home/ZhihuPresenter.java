package com.lws.zhiqu.presenter.home;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.home.ZhihuContract;
import com.lws.zhiqu.model.bean.ZhuDetailBean;
import com.lws.zhiqu.model.bean.ZhuListBean;
import com.lws.zhiqu.model.home.ZhihuModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by song on 2018/2/3.
 */

public class    ZhihuPresenter extends BasePresenter<ZhihuContract.View> implements ZhihuContract.Presenter {
    private ZhihuModel mZhihuModel;

    public ZhihuPresenter() {
        mZhihuModel = new ZhihuModel();
    }

    @Override
    public void loadData() {
        mZhihuModel.getLatestData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhuListBean>() {
                    @Override
                    public void accept(ZhuListBean zhuListBean) throws Exception {
                        if (view != null) {
                            view.returnData(zhuListBean, false);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();

                    }
                });

    }

    @Override
    public void loadMore(String time) {
        mZhihuModel.getBeforeData(time).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhuListBean>() {
                    @Override
                    public void accept(ZhuListBean zhuListBean) throws Exception {
                        view.returnData(zhuListBean,true);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();

                    }
                });

    }

    @Override
    public void losdDetail(String id) {
        mZhihuModel.getDetail(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZhuDetailBean>() {
                    @Override
                    public void accept(ZhuDetailBean zhuDetailBean) throws Exception {
                        view.returnData(zhuDetailBean,false);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError();

                    }
                });
    }
}
