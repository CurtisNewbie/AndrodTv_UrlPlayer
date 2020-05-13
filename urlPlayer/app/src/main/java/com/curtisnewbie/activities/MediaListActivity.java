package com.curtisnewbie.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.curtisnewbie.services.App;
import com.curtisnewbie.services.HttpService;

import javax.inject.Inject;

/**
 * ------------------------------------
 * <p>
 * Author: Yongjie Zhuang
 * <p>
 * ------------------------------------
 * <p>
 * Activity that displays the list of media that is fetched from the backend media hoster
 */
public class MediaListActivity extends AppCompatActivity {

    @Inject
    protected HttpService httpService;

    protected RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MediaListAdapter());
    }
}
