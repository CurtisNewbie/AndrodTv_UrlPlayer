package com.curtisnewbie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.curtisnewbie.services.App;
import com.curtisnewbie.services.HttpService;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    HttpService httpService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpService.init("192.168.1.12");
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = httpService.getMediaList();
                for (String s : list) {
                    System.out.println("URLPlayer " + s);
                }
            }
        }).start();
    }
}
