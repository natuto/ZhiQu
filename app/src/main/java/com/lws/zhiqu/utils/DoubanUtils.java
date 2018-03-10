package com.lws.zhiqu.utils;

import com.lws.zhiqu.model.bean.DouDetailBean;
import com.lws.zhiqu.model.bean.DoubanBean;

/**
 * Created by song on 2018/3/1.
 */

public class DoubanUtils {
    public static String getAvatars(DoubanBean doubanBean ,int p){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i < doubanBean.getSubjects().get(p).getCasts().size(); i++) {
            if (i == 0) {
                stringBuilder.append(doubanBean.getSubjects().get(p).getCasts().get(i).getName());
            }else {
                stringBuilder.append("/"+doubanBean.getSubjects().get(p).getCasts().get(i).getName());
            }

        }


        return stringBuilder.toString();
    }
    public static String getGenres(DoubanBean doubanBean ,int p){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i < doubanBean.getSubjects().get(p).getGenres().size(); i++) {
            if (i == 0) {
                stringBuilder.append(doubanBean.getSubjects().get(p).getGenres().get(i));
            }else {
                stringBuilder.append("/"+doubanBean.getSubjects().get(p).getGenres().get(i));
            }

        }

        return stringBuilder.toString();
    }
    public static String getCasts(DouDetailBean doubanBean ){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i < doubanBean.getCasts().size(); i++) {
            if (i == 0) {
                stringBuilder.append(doubanBean.getCasts().get(i).getName());
            }else {
                stringBuilder.append("/"+doubanBean.getCasts().get(i).getName());
            }

        }

        return stringBuilder.toString();
    }
    public static String getDetailGenres(DouDetailBean doubanBean ){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0; i < doubanBean.getGenres().size(); i++) {
            if (i == 0) {
                stringBuilder.append(doubanBean.getGenres().get(i));
            }else {
                stringBuilder.append("/"+doubanBean.getGenres().get(i));
            }

        }

        return stringBuilder.toString();
    }



}
