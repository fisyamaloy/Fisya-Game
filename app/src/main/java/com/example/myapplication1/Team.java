package com.example.myapplication1;

import java.util.ArrayList;
import java.util.List;

public class Team {
    public List<String> CorrectAnswers = new ArrayList<>();
    public List<String> WrongAnswers = new ArrayList<>();

    private String _name;
    private int _mark;

    private static int _roundTime;
    private static int _roundsQuantity;

    public Team(String Name,int RoundsQuantity, int RoundTime){
        _name = Name;
        _roundTime = RoundTime;
        _roundsQuantity = RoundsQuantity;
    }

    public String GetName(){ return _name; }
    public int GetMark(){ return _mark; }
    public void SetMark(int value){ _mark = value; }

    public static int GetRoundTime(){ return _roundTime; }
    public static int GetRoundsQuantity(){return _roundsQuantity;}
}
