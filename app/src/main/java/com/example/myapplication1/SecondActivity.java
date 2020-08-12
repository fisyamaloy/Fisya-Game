package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_second);

        if (IsNotTheLastTeamAtList())
            SetMessageText();
    }

    private void SetMessageText() {
        TextView ViewReady = findViewById(R.id.ViewReady);
        ViewReady.setText(getString(R.string.welcome_messages, Lists.Teams.get(Lists.TeamPosInList).GetName()));
    }

    public void StartGameClick(View v) {
        if (IsNotTheLastTeamAtList()) {
            Intent intent = new Intent(this, PlayActivity.class);
            startActivity(intent);
        }
    }

    private boolean IsNotTheLastTeamAtList() {
        return Lists.TeamPosInList < Lists.Teams.size();
    }
}