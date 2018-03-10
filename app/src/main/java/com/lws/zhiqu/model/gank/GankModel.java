package com.lws.zhiqu.model.gank;

import com.lws.zhiqu.api.GankApi;
import com.lws.zhiqu.contract.gank.GankContract;
import com.lws.zhiqu.model.bean.GankBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/26.
 */

public class GankModel implements GankContract.Model {
    @Override
    public Observable<GankBean> getLatestData(String type ,int pageSize, int page) {
        return RetrofitUtils.createApi(GankApi.class,GankApi.HOST).getGankList(type ,pageSize,page);
    }
}
