package com.lws.zhiqu.contract.home;

import com.lws.zhiqu.base.IBaseModel;
import com.lws.zhiqu.base.IBaseView;
import com.lws.zhiqu.model.bean.WeiXinBean;
import com.lws.zhiqu.model.bean.ZhuListBean;

import io.reactivex.Observable;

import static android.R.attr.key;

/**
 * Created by song on 2018/2/20.
 */

public interface WinxinContract {
    interface View<T> extends IBaseView {
        void returnData(T data, boolean isPege);

    }
    interface Presenter {
        void loadLatest();
        void loadMore();

    }
    interface Model extends IBaseModel{
        Observable<WeiXinBean> getLatestData(int pno, int ps, String dtype, String key);
    }

}
