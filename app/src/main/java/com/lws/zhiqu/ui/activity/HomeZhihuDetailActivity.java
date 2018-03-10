package com.lws.zhiqu.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseThemeActivity;
import com.lws.zhiqu.contract.home.ZhihuContract;
import com.lws.zhiqu.model.bean.ZhuDetailBean;
import com.lws.zhiqu.presenter.home.ZhihuPresenter;
import com.lws.zhiqu.utils.HtmlUtils;

import butterknife.BindView;

public class HomeZhihuDetailActivity extends BaseThemeActivity implements ZhihuContract.View<ZhuDetailBean> {
    @BindView(R.id.ivImage)
    ImageView mImageView;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTextView;
    private ZhihuPresenter mZhiHuPresenter;

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home_zhihu_detail;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        initToobar();
        setWebView();
        initData();
    }
    protected void initData(){
        String id = getIntent().getStringExtra("id");
        mZhiHuPresenter = new ZhihuPresenter();
        mZhiHuPresenter.attchView(this);
        mZhiHuPresenter.losdDetail(id);
    }
    protected void initToobar(){
        mToolbar.setTitle("正在加载中...");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    protected void setWebView(){

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mToolbar.setTitle("知乎日报");

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
        if (mZhiHuPresenter != null) {
            mZhiHuPresenter.detchView();

        }
    }



    @Override
    public void returnData(ZhuDetailBean data, boolean isPege) {
        Glide.with(this).load(data.getImage()).crossFade().into(mImageView);
        mTextView.setText(data.getTitle());
        String html = HtmlUtils.createHtmlData(data.getBody(), data.getCss(),data.getJs());
        mWebView.loadData(html, HtmlUtils.MIME_TYPE, HtmlUtils.ENCODING);

    }

    @Override
    public void showError() {
        Toast.makeText(this,"网络出错",Toast.LENGTH_SHORT).show();

    }
}
