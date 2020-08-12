package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_input);

        ChangeEnableFieldsOn(true);
        Lists.Words.addAll(Arrays.asList(Lists._words));
    }

    public void AddTeamOnClick(View vm) {
        try {
            EditText NameT = findViewById(R.id.NameTeam);
            String NameTeam = NameT.getText().toString().trim();

            int RoundsQuantity = GetValueOf(R.id.RoundsQuantity);
            int RoundTime = GetValueOf(R.id.RoundTime);

            if (FieldsAreNotEmpty(NameTeam, Integer.toString(RoundsQuantity), Integer.toString(RoundTime))) {
                Lists.Teams.add(new Team(NameTeam, RoundsQuantity, RoundTime));
                NameT.setText("");
                ChangeEnableFieldsOn(false);
            } else
                ShowMessage("Заполните пустые поля");
        } catch (Exception e) {
            ShowMessage("Заполните пустые поля");
        }
    }

    private void ChangeEnableFieldsOn(boolean b) {
        EditText edit = findViewById(R.id.RoundsQuantity);
        edit.setEnabled(b);
        EditText edit1 = findViewById(R.id.RoundTime);
        edit1.setEnabled(b);
    }

    private int GetValueOf(int num) {
        EditText edit = findViewById(num);
        return Integer.parseInt(edit.getText().toString().trim());
    }

    private boolean FieldsAreNotEmpty(String... values) {
        for (String text : values) {
            if (text.trim().isEmpty())
                return false;
        }
        return true;
    }

    public void ChangeActivityClick(View v) {
        if (Lists.Teams.size() != 0) {
            Lists.ShuffleWords();

            Intent intent = new Intent("com.example.myapplication1.SecondActivity");
            startActivity(intent);
        } else
            ShowMessage("Добавьте команду");
    }

    private void ShowMessage(String row) {
        Toast.makeText(
                InputActivity.this, row,
                Toast.LENGTH_LONG
        ).show();
    }
}