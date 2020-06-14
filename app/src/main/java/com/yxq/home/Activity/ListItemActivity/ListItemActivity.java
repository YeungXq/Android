package com.yxq.home.Activity.ListItemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yxq.home.Activity.BaseActivity;
import com.yxq.home.R;

/**
 * Created by 残 on 2019/11/23.
 */

public class ListItemActivity extends BaseActivity {
    WebView webView;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_web);
        //把网页嵌入到安卓项目内，不使用浏览器打开
        webView = (WebView) findViewById(R.id.list_item_web);
        intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        webView.loadUrl(url);
        setTitle(title);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        //允许使用javascript，变成手机版网页
        webSettings.setJavaScriptEnabled(true);
    }
}
