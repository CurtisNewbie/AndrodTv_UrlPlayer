package com.curtisnewbie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class MediaPlayerActivity extends AppCompatActivity {

    private String url;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        url = getIntent().getStringExtra(MediaListAdapter.URL_TAG);
        videoView = findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse(url));
        videoView.start();
    }
}
