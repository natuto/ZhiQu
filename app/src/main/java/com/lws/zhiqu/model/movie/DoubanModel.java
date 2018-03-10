package com.lws.zhiqu.model.movie;

import com.lws.zhiqu.R;
import com.lws.zhiqu.api.DoubanApi;
import com.lws.zhiqu.contract.movie.MovieContract;
import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.model.bean.DoubanBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;

/**
 * Created by song on 2018/2/28.
 */

public class DoubanModel implements MovieContract.Model {
    @Override
    public Observable<DoubanBean> getLatestData() {
        return RetrofitUtils.createApi(DoubanApi.class, DoubanApi.HOST).getMovieList();
    }

    @Override
    public Observable<DouDetailBean> getDetailData(String id) {
        return RetrofitUtils.createApi(DoubanApi.class, DoubanApi.HOST).getDouDetail(id);
    }
}
