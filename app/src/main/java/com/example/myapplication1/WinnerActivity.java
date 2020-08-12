package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class WinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_winner);

        final int MaxTeamIndex = FindTeamIndexWithMaxMark();
        ShowWinnerTeamText(MaxTeamIndex);
    }

    private int FindTeamIndexWithMaxMark(){
        int TeamIndexWithMaxMark = 0;
        int MaxMark = Lists.Teams.get(0).GetMark();
        for(int i = 1; i < Lists.Teams.size(); i++){
            int TempMark = Lists.Teams.get(i).GetMark();
            if(TempMark > MaxMark){
                MaxMark = TempMark;
                TeamIndexWithMaxMark = i;
            }
        }
        return TeamIndexWithMaxMark;
    }

    private void ShowWinnerTeamText(int index){
        TextView textView = findViewById(R.id.team);
        textView.setText(Lists.Teams.get(index).GetName());
    }

    public void ChangeActivityClick(View v){
        Lists.Teams = new ArrayList<>();
        Lists.TeamPosInList = 0;

        Intent intent = new Intent(this, InputActivity.class);
        startActivity(intent);
    }
}