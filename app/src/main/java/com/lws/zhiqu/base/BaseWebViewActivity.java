package com.lws.zhiqu.base;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lws.zhiqu.R;

import butterknife.BindView;

/**
 * Created by song on 2018/2/16.
 */

public class BaseWebViewActivity extends BaseThemeActivity {
    @BindView(R.id.webView)
    WebView mWebView;
    @Override
    protected int getlayoutId() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
    protected void setWebView(final String url){
        mWebView.loadUrl(url);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.destroy();
        }
    }
}
