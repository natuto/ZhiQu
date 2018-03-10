package com.lws.zhiqu.contract.home;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.ZhuDetailBean;
import com.lws.zhiqu.model.bean.ZhuListBean;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/3.
 */

public interface ZhihuContract {
   interface View<T> extends IBaseView{
       void returnData(T data, boolean isPege);


    }
    interface Presenter {
         void loadData();
         void loadMore(String tiem);
         void losdDetail(String id);


    }
    interface Model extends IBaseModel{
        Observable<ZhuListBean> getLatestData();
        Observable<ZhuListBean> getBeforeData(String tiem);
        Observable<ZhuDetailBean> getDetail(String id);

    }

}
