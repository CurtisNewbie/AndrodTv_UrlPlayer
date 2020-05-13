package com.curtisnewbie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    protected EditText serverIpEt;
    protected Button connectServerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.serverIpEt = findViewById(R.id.serverIpEt);
        this.serverIpEt.setText("192.168.1."); // easier to enter the ip (change it if necessary)
        this.connectServerBtn = findViewById(R.id.connectServerBtn);

        this.connectServerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip = serverIpEt.getText().toString().trim();
                if (!ip.isEmpty()) {
                    httpService.init(ip);
                    Intent intent = new Intent(".MediaListActivity");
                    startActivity(intent);
                }
            }
        });
    }
}
