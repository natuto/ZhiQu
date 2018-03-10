package com.lws.zhiqu.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lws.zhiqu.R;
import com.lws.zhiqu.base.BaseThemeActivity;

import butterknife.BindView;

import static android.R.attr.data;

public class HomeNewsDetailActivity extends BaseThemeActivity{
    @BindView(R.id.ivImage)
    ImageView mImageView;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_title)
    TextView mTextView;

    @Override
    protected int getlayoutId() {
        return R.layout.activity_home_news_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mToolbar.setTitle("正在加载中...");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = getIntent().getBundleExtra("data");
        String url = bundle.getString("url");
        String image = bundle.getString("image");
        Glide.with(this).load(image).crossFade().into(mImageView);
        mWebView.loadUrl(url);
        setWebView();
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
                mToolbar.setTitle("新闻资讯");

            }
        });

    }
}
