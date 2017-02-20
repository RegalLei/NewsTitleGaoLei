package com.gl.newtitlegaolei.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.gl.newtitlegaolei.R;

public class HomeWebViewActivity extends AppCompatActivity {

    private WebView home_tv_webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_view);

        initView();

    }

    private void initView() {
        home_tv_webview = (WebView) findViewById(R.id.home_tv_webview);
        Intent intent = getIntent();
        String hurls = intent.getStringExtra("urls");

//        home_tv_webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        home_tv_webview.getSettings().setJavaScriptEnabled(true);
        home_tv_webview.loadUrl(hurls);
//        home_tv_webview.setWebViewClient(new WebViewClient());

    }
}
