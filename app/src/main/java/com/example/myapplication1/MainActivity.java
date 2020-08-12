package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar _progressBar;
    private Handler _handler;
    private int _progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        _handler = new Handler();
        _progressBar = findViewById(R.id.progressBar);
        _progress = 0;

        LoadingBar();
    }

    private void LoadingBar(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (_progress < 100)
                    PartLoading();
                Intent intent = new Intent("com.example.myapplication1.InputActivity");
                startActivity(intent);
            }

        }).start();
    }

    private void PartLoading() {
        _handler.post(new Runnable() {
            @Override
            public void run() {
                _progressBar.setProgress(_progress);
            }
        });
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        _progress++;
    }
}