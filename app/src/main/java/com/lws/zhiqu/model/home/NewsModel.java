package com.lws.zhiqu.model.home;

import com.lws.zhiqu.api.NewsApi;
import com.lws.zhiqu.contract.home.NewsContract;
import com.lws.zhiqu.model.bean.NewsBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;



/**
 * Created by song on 2018/2/24.
 */

public class NewsModel implements NewsContract.Model{
    @Override
    public Observable<NewsBean> getLatestData(int id) {
        return RetrofitUtils.createApi(NewsApi.class,NewsApi.HOST).getNewsList( id);
    }
}
