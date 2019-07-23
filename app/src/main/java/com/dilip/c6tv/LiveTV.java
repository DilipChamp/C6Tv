package com.dilip.c6tv;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class LiveTV extends AppCompatActivity {
ProgressDialog progressDialog;
VideoView videoView;
String VideoUrl="https://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_tv);
        videoView=(VideoView)findViewById(R.id.VideoView);
        progressDialog=new ProgressDialog(LiveTV.this);
        progressDialog.setTitle("Live Streaming Video");
        progressDialog.setMessage("under construction coming soon....");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
        try
        {
            MediaController mediaController=new MediaController(LiveTV.this);
            mediaController.setAnchorView(videoView);
            Uri video=Uri.parse(VideoUrl);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);
        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                videoView.start();
            }
        });

    }

}
