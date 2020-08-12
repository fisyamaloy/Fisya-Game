package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PlayActivity extends AppCompatActivity {
    private int _seconds;
    private int _roundsQuantity;
    private boolean _running;
    private int _mark;

    private MediaPlayer _greenSound, _redSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_play);

        LetsPlay();
    }

    private void LetsPlay(){
        _greenSound = MediaPlayer.create(this, R.raw.krasavchik);
        _redSound = MediaPlayer.create(this, R.raw.lox);

        _running = true;
        _mark = 0;
        _seconds = Team.GetRoundTime();
        _roundsQuantity = Team.GetRoundsQuantity();
        SetWord();
        RunTimer();
    }

    private void RunTimer() {
        final Button text = findViewById(R.id.GreenBut);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                if (_running) {
                    if (_seconds <= 0) {
                        SoundPlay(_redSound);
                        final TextView word = findViewById(R.id.WordView);
                        Lists.Teams.get(Lists.TeamPosInList).WrongAnswers.add(word.getText().toString());
                        SetActions(text);
                    }
                    handler.postDelayed(this, 1000);
                    _seconds--;
                    text.setText(Integer.toString(_seconds));
                }
            }
        });
    }

    private void SetWord() {
        String word = Lists.Words.get(0);
        Lists.Words.remove(0);
        TextView WordText = findViewById(R.id.WordView);
        WordText.setText(word);
        _roundsQuantity--;
    }

    public void GreenButClick(View v) throws InterruptedException {
        ActionsAfterButtonClick(_greenSound, 1650, Lists.Teams.get(Lists.TeamPosInList).CorrectAnswers, 1);
    }

    public void RedButClick(View v) throws InterruptedException {
        ActionsAfterButtonClick(_redSound, 1000, Lists.Teams.get(Lists.TeamPosInList).WrongAnswers, 0);
    }

    private void ActionsAfterButtonClick(MediaPlayer sound, long millis, List<String> list, int number) throws InterruptedException {
        SetOppositeButtonsEnable(R.id.GreenBut, R.id.RedBut);

        SoundPlay(sound);
        Thread.sleep(millis);

        final TextView text = findViewById(R.id.WordView);
        list.add(text.getText().toString());

        _mark += number;

        SetActions((Button)findViewById(R.id.GreenBut));

        SetOppositeButtonsEnable(R.id.GreenBut, R.id.RedBut);
    }

    private void SoundPlay(MediaPlayer sound){ sound.start(); }

    private void SetOppositeButtonsEnable(int... ButtonsIdes){
        for(int value : ButtonsIdes){
            Button button = findViewById(value);
            button.setEnabled(!button.isEnabled());
        }
    }

    @SuppressLint("SetTextI18n")
    private void SetActions(Button text) {
        if (_roundsQuantity > 0) {
            SetWord();
            _seconds = Team.GetRoundTime();
            text.setText(Integer.toString(_seconds));
        } else {
            _running = false;
            Lists.Teams.get(Lists.TeamPosInList).SetMark(_mark);

            Intent intent = new Intent(this, ResultsActivity.class);
            startActivity(intent);
        }
    }
}