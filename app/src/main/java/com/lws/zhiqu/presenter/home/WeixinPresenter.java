package com.lws.zhiqu.presenter.home;

import com.lws.zhiqu.App;
import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.home.WinxinContract;
import com.lws.zhiqu.model.bean.WeiXinBean;
import com.lws.zhiqu.model.home.WeixinModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.lws.zhiqu.App.WEI_XIN_KEY;

/**
 * Created by song on 2018/2/20.
 */

public class WeixinPresenter extends BasePresenter<WinxinContract.View> implements WinxinContract.Presenter {
    private  int mPno;
    private  int mPs = 20;
    private  String mDtype = "";
    private WeixinModel mWeixinModel;

    public WeixinPresenter() {
        mWeixinModel = new WeixinModel();

    }

    @Override
    public void loadLatest() {
        mPno = 1;
        mWeixinModel.getLatestData( mPno, mPs, mDtype, WEI_XIN_KEY).
               subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WeiXinBean>() {
           @Override
           public void accept(WeiXinBean weiXinBean) throws Exception {

                view.returnData(weiXinBean, false);
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
        mPno++;
        mWeixinModel.getLatestData( mPno, mPs,mDtype ,App.WEI_XIN_KEY).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WeiXinBean>() {
            @Override
            public void accept(WeiXinBean weiXinBean) throws Exception {


                view.returnData(weiXinBean, true);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();


            }
        });

    }
}
