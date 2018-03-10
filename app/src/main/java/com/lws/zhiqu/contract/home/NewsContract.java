package com.lws.zhiqu.contract.home;

import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.NewsBean;
import com.lws.zhiqu.model.bean.WeiXinBean;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/24.
 */
public interface NewsContract {
    interface View extends IBaseView{
        void returnData(NewsBean data, boolean isPege);

    }

    interface Presenter {
        void loadLatest();
        void loadMore();
    }
    interface Model extends IBaseModel {
        Observable<NewsBean> getLatestData(int id);
    }

}
