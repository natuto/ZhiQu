package com.lws.zhiqu.contract.gank;

import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.model.bean.WeiXinBean;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/26.
 */

public interface GankContract {
    interface View extends IBaseView{
        void returnData(GankBean gankBean, boolean isPage);
    }
    interface Presenter {
        void loadLatest(String type);
        void loadMore(String type);

    }
    interface Model extends IBaseModel {
        Observable<GankBean> getLatestData(String type ,int pageSize, int page);
    }
}
