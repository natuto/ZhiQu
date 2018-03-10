package com.lws.zhiqu.presenter.home;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.home.NewsContract;
import com.lws.zhiqu.model.bean.NewsBean;
import com.lws.zhiqu.model.home.NewsModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by song on 2018/2/24.
 */

public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    private NewsModel mNewsModel;
    private  int id;
    public NewsPresenter() {
        mNewsModel = new NewsModel();

    }

    @Override
    public void loadLatest() {
        id = 0;
        mNewsModel.getLatestData(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NewsBean>() {
            @Override
            public void accept(NewsBean newsBean) throws Exception {
                for (int i = 0; i < newsBean.getT1348647909107().size(); i++) {
                    if ( newsBean.getT1348647909107().get(i).getUrl() == null) {
                        newsBean.getT1348647909107().remove(i);
                    }
                }
                view.returnData(newsBean,false);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });

    }

    @Override
    public void loadMore() {
        id += 20 ;
        mNewsModel.getLatestData(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<NewsBean>() {
            @Override
            public void accept(NewsBean newsBean) throws Exception {

                view.returnData(newsBean,false);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });

    }
}
