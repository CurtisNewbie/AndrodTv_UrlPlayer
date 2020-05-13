package com.curtisnewbie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.curtisnewbie.services.App;
import com.curtisnewbie.services.HttpService;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected HttpService httpService;

    protected EditText serverIpEt = findViewById(R.id.serverIpEt);
    protected Button connectServerBtn = findViewById(R.id.connectServerBtn);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.connectServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = serverIpEt.getText().toString().trim();
                if (!ip.isEmpty()) {
                    httpService.init(ip);
                }
            }
        });
    }
}

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<String> list = httpService.getMediaList();
//                for (String s : list) {
//                    System.out.println("URLPlayer " + s);
//                }
//            }
//        }).start();
