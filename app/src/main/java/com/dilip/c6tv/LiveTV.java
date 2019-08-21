package com.dilip.c6tv;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;





public class LiveTV extends AppCompatActivity {

    WebView views;
    String html="<iframe src='http://www.ssh101.com/securelive/index.php?id=c6tv' width=1920 height=1080></iframe>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);
        views= (WebView) findViewById(R.id.webViews);
        views.getSettings().setJavaScriptEnabled(true);
        views.loadData(html, "text/html", null);

    }

}
