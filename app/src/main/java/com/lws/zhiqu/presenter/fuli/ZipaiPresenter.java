package com.lws.zhiqu.presenter.fuli;

import android.provider.DocumentsContract;

import com.lws.zhiqu.base.BasePresenter;
import com.lws.zhiqu.contract.fuli.FuliContract;
import com.lws.zhiqu.model.bean.GirlBean;
import com.lws.zhiqu.model.bean.JiandanBean;
import com.lws.zhiqu.model.fuli.FuliModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by song on 2018/3/5.
 */

public class ZipaiPresenter extends BasePresenter <FuliContract.View>implements FuliContract.Presenter {
    private  int page;
    private FuliModel mFuliModel;
    public ZipaiPresenter() {
        mFuliModel = new FuliModel();

    }

    @Override
    public void loadLatest() {
        page = 1;
        mFuliModel.getZipaiData(page).subscribeOn(Schedulers.io()).map(new Function<ResponseBody,List<String>>() {
            /**
             * Apply some calculation to the input value and return some other value.
             *
             * @param responseBody the input value
             * @return the output value
             * @throws Exception on error
             */
            @Override
            public List<String> apply(ResponseBody responseBody) throws Exception {
                List<String> list = new ArrayList<String>();
                org.jsoup.nodes.Document doc = Jsoup.parse(responseBody.string());
                Element total = doc.select("div.postlist").first();
                Elements items = total.select("li");
                for (Element element : items) {
                    list.add(element.select("img").first().attr("src"));
                }

                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                view.returnData(strings, false);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });





    }

    @Override
    public void loadMore() {
        page ++;
        mFuliModel.getZipaiData(page).subscribeOn(Schedulers.io()).map(new Function<ResponseBody,List<String>>() {
            /**
             * Apply some calculation to the input value and return some other value.
             *
             * @param responseBody the input value
             * @return the output value
             * @throws Exception on error
             */
            @Override
            public List<String> apply(ResponseBody responseBody) throws Exception {
                List<String> list = new ArrayList<String>();
                org.jsoup.nodes.Document doc = Jsoup.parse(responseBody.string());
                Element total = doc.select("div.postlist").first();
                Elements items = total.select("li");
                for (Element element : items) {
                    list.add(element.select("img").first().attr("src"));
                }

                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                view.returnData(strings, true);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                view.showError();
            }
        });
    }
}
