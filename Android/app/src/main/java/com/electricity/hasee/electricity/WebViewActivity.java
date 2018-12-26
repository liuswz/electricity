package com.electricity.hasee.electricity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.stx.xhb.xbanner.XBanner;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class WebViewActivity extends AppCompatActivity {

    @ViewInject(R.id.webview)
    private WebView webView;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        x.view().inject(this);
        Intent intent = getIntent();//获取传来的intent对象
        url = intent.getStringExtra("url");//获取键值对的键名

        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();


//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); //

    }
}
