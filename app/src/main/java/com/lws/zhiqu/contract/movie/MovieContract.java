package com.lws.zhiqu.contract.movie;

import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.model.bean.DoubanBean;
import com.lws.zhiqu.model.bean.NewsBean;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/28.
 */

public interface MovieContract {
    interface View<T> extends IBaseView{
        void returnData(T doubanBean);

    }

    interface Presenter {
        void loadLatest();
        void loadDetail(String id);

    }
    interface Model extends IBaseModel {
        Observable<DoubanBean> getLatestData();
        Observable<DouDetailBean> getDetailData(String id);
    }

}
