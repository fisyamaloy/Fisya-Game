package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private int _maxMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_results);

        AddMarkToViewText();
        AddWordsFromWrongListToViewList();
    }

    public void ChangeActivityClick(View v) {
        Lists.TeamPosInList++;
        if(IsNotTheLastTeamAtList()) {
            ShowActivity("com.example.myapplication1.SecondActivity");
        }else if (GetTeamsQuantityWithTheSameMaxMark() > 1) {
            Lists.TeamPosInList = 0;
            RemoveTeamsWithNotMaxMark();

            for(Team team : Lists.Teams) {
                team.WrongAnswers = new ArrayList<>();
                team.CorrectAnswers = new ArrayList<>();
            }

            ShowActivity("com.example.myapplication1.SecondActivity");
        }else{
            ShowActivity("com.example.myapplication1.WinnerActivity");
        }
    }

    private boolean IsNotTheLastTeamAtList(){
        return Lists.TeamPosInList < Lists.Teams.size();
    }

    private void ShowActivity(String activity){
        Intent intent = new Intent(activity);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    private void AddMarkToViewText(){
        int mark = Lists.Teams.get(Lists.TeamPosInList).GetMark();
        TextView text = findViewById(R.id.Number);
        text.setText(Integer.toString(mark));
    }

    private void AddWordsFromWrongListToViewList(){
        List<String> _wrongWords = Lists.Teams.get(Lists.TeamPosInList).WrongAnswers;
        ListView _wrong = findViewById(R.id.WrongList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.words, _wrongWords);
        _wrong.setAdapter(adapter);
    }

    private int GetTeamsQuantityWithTheSameMaxMark(){
        _maxMark = GetMaxMarkBetweenTeams();
        int TeamsQuantityWithTheSameMark = 0;
        for(Team team : Lists.Teams){
            if(team.GetMark() == _maxMark)
                TeamsQuantityWithTheSameMark++;
        }
        return TeamsQuantityWithTheSameMark;
    }

    private int GetMaxMarkBetweenTeams(){
        int MaxMark = Lists.Teams.get(0).GetMark();
        for(Team team : Lists.Teams){
            int TeamMark = team.GetMark();
            if(TeamMark > MaxMark)
                MaxMark = TeamMark;
        }
        return  MaxMark;
    }

    private void RemoveTeamsWithNotMaxMark(){
        int TeamsQuantity = Lists.Teams.size();
        for(int i = 0; i < TeamsQuantity; i++){
            if(_maxMark != Lists.Teams.get(i).GetMark()){
                Lists.Teams.remove(i);
                i--;
                TeamsQuantity--;
            }
        }
    }
}