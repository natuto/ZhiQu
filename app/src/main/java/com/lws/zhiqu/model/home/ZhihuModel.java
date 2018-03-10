package com.lws.zhiqu.model.home;

import com.lws.zhiqu.api.ZhuhuApi;
import com.lws.zhiqu.contract.home.ZhihuContract;
import com.lws.zhiqu.model.bean.ZhuDetailBean;
import com.lws.zhiqu.model.bean.ZhuListBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/10.
 */

public class ZhihuModel implements ZhihuContract.Model {

    @Override
    public Observable<ZhuListBean> getLatestData() {
        return RetrofitUtils.createApi(ZhuhuApi.class,ZhuhuApi.HOST).getLatestList();
    }

    @Override
    public Observable<ZhuListBean> getBeforeData(String time) {

        return RetrofitUtils.createApi(ZhuhuApi.class,ZhuhuApi.HOST).getBeforeList(time);
    }

    @Override
    public Observable<ZhuDetailBean> getDetail(String id) {
        return RetrofitUtils.createApi(ZhuhuApi.class,ZhuhuApi.HOST).getDetail(id);
    }
}
