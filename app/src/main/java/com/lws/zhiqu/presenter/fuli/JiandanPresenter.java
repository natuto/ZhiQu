package com.lws.zhiqu.presenter.fuli;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.fuli.FuliContract;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.model.fuli.FuliModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by song on 2018/3/5.
 */

public class JiandanPresenter extends BasePresenter <FuliContract.View>implements FuliContract.Presenter {
    private  int page;
    private FuliModel mFuliModel;
    public JiandanPresenter() {
        mFuliModel = new FuliModel();

    }

    @Override
    public void loadLatest() {
        page = 1;
        mFuliModel.getJiandanData(page).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).
                      subscribe(new Consumer<JiandanBean>() {
                                    @Override
                                    public void accept(JiandanBean jiandanBean) throws Exception {

                                        view.returnData(jiandanBean, false);
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        view.showError();
                                    }
                                }
                      );

    }

    @Override
    public void loadMore() {
        page ++;
        mFuliModel.getJiandanData(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Consumer<JiandanBean>() {
                              @Override
                              public void accept(JiandanBean jiandanBean) throws Exception {

                                  view.returnData(jiandanBean, true);
                              }
                          }, new Consumer<Throwable>() {
                              @Override
                              public void accept(Throwable throwable) throws Exception {
                                  view.showError();
                              }
                          }
                );


    }
}
