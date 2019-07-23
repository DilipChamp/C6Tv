package com.dilip.c6tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends AppCompatActivity {
    WebView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        String url="http://c6tv.in/";
        view=(WebView) this.findViewById(R.id.web);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
        view.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if(view.canGoBack())
        {
            view.goBack();
        }else
        {
            super.onBackPressed();
        }
    }
}
