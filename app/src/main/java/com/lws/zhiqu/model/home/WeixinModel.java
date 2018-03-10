package com.lws.zhiqu.model.home;

import com.lws.zhiqu.api.WeixinApi;
import com.lws.zhiqu.contract.home.WinxinContract;
import com.lws.zhiqu.model.bean.WeiXinBean;
import com.lws.zhiqu.utils.RetrofitUtils;

import io.reactivex.Observable;



/**
 * Created by song on 2018/2/20.
 */

public class WeixinModel implements WinxinContract.Model {
    @Override
    public Observable<WeiXinBean> getLatestData(int pno, int ps,String dtype, String key) {

        return RetrofitUtils.createApi(WeixinApi.class,WeixinApi.HOST).getWeixinList( pno, ps, dtype ,key);
    }


}
