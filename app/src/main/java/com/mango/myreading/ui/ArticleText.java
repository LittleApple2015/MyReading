package com.mango.myreading.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mango.myreading.R;

/**
 * Created by Administrator on 2015/9/8 0008.
 */
public class ArticleText extends Activity {

    private WebView mWebView;
    private String textUrl;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_text);
        initView(); //初始化控件
        getUrl(); //获取地址
        setWebView(); //设置属性
        getData(); //显示内容

    }

    private void setWebView() {
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setDefaultFontSize(18);
    }



    private void getData() {
        mWebView.loadUrl(textUrl);
    }

    private void getUrl() {
        Bundle bundle = getIntent().getExtras();
        textUrl = bundle.getString("textUrl");
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.article_text);
    }

}
