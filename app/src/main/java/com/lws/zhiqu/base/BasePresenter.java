package com.lws.zhiqu.base;

/**
 * Created by song on 2018/2/3.
 */

public abstract class BasePresenter<T> {
    public T view;


    public  void attchView(T view){
        this.view = view;
    }
    public void detchView(){
        if (view != null) {
            this.view = null;
        }

    }
}
