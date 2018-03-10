package com.lws.zhiqu.contract.fuli;

import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by song on 2018/3/5.
 */

public interface FuliContract {
    interface View<T> extends IBaseView {
        void returnData(T data, boolean isPage);
    }
    interface Presenter {
        void loadLatest();
        void loadMore();

    }
    interface Model extends IBaseModel {
        Observable<GirlBean> getGirlData( int page);
        Observable<JiandanBean> getJiandanData(int page);
        Observable<ResponseBody> getZipaiData(int page);
    }
}
