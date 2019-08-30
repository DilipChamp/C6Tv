package com.dilip.c6tv;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
// Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);
// Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void webview(View view) {
        Intent webv=new Intent(Home.this,Web.class);
        startActivity(webv);
    }

    public void youtube(View view) {
        Intent you=new Intent(Home.this,Youtube.class);
        startActivity(you);
    }

    public void liveTV(View view) {
        Intent live=new Intent(Home.this,LiveTV.class);
        startActivity(live);
    }

    public void share(View view) {
        Intent share=new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT,"C6TV Channel App");
        share.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.dilip.c6tv");
        startActivity(Intent.createChooser(share,"Share link!"));
    }

    public void facebook(View view) {
        String pageurl="https://www.facebook.com/C6TVCHANNEL";
        Intent fbi=new Intent(Intent.ACTION_VIEW,Uri.parse(pageurl));
        startActivity(fbi);
    }

    public void twitter(View view) {
        String twi="https://twitter.com/C6tv_in";
        Intent twit=new Intent(Intent.ACTION_VIEW,Uri.parse(twi));
        startActivity(twit);
//        Intent in=null;
//        try
//        {
//            this.getPackageManager().getPackageInfo("com.twitter.android",0);
//            in=new Intent(Intent.ACTION_VIEW,Uri.parse("twitter://user?user_id=1055945496622256128"));
//            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        catch(Exception e)
//        {
//            in=new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/C6tv_in"));
//        }
//        this.startActivity(in);
    }

    public void instagram(View view) {
        Uri uri=Uri.parse("https://instagram.com/_u/c6tv.in");
        Intent insta=new Intent(Intent.ACTION_VIEW,uri);
        insta.setPackage("com.instagram.android");
        try
        {
            startActivity(insta);
        }
        catch (ActivityNotFoundException e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://instagram.com/c6tv.in")));
        }
    }

    public void telegram(View view) {
        Intent tele=new Intent(Intent.ACTION_VIEW,Uri.parse("https://t.me/C6tvChannel"));
        startActivity(tele);
    }

    public void whatsapp(View view) {
        Uri uri=Uri.parse("https://chat.whatsapp.com/BTU6rr3ME3o7fgM9CcFTO1");
        Intent what=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(Intent.createChooser(what,""));

    }
}
