package com.lws.zhiqu.model.fuli;

import com.lws.zhiqu.api.FuliApi;
import com.lws.zhiqu.api.GankApi;
import com.lws.zhiqu.contract.fuli.FuliContract;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by song on 2018/3/5.
 */

public class FuliModel implements FuliContract.Model {
    @Override
    public Observable<GirlBean> getGirlData(int page) {
        return RetrofitUtils.createApi(FuliApi.class, GankApi.HOST).getGirllist(page);
    }

    @Override
    public Observable<JiandanBean> getJiandanData(int page) {
        return RetrofitUtils.createApi(FuliApi.class,FuliApi.HOST).getJiandan(page);
    }

    @Override
    public Observable<ResponseBody> getZipaiData(int page) {
        return RetrofitUtils.createApi(FuliApi.class , FuliApi.MZITU_HOST).getZipailist(page);
    }
}
