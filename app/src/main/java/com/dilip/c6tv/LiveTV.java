package com.dilip.c6tv;


import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class LiveTV extends AppCompatActivity {
    WebView views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        views= (WebView)findViewById(R.id.webViews);
        views.setInitialScale(1);
        views.setWebChromeClient(new WebChromeClient());
        views.getSettings().setAllowFileAccess(true);
        views.getSettings().setPluginState(WebSettings.PluginState.ON);
        views.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        views.setWebViewClient(new WebViewClient());
        views.getSettings().setJavaScriptEnabled(true);
        views.getSettings().setLoadWithOverviewMode(true);
        views.getSettings().setUseWideViewPort(true);
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        views.loadUrl("http://ad.mysisli.com/live/c6tv/index.m3u8");
    }

}
