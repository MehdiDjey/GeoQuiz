package com.example.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HighScoreQuestionActivity extends AppCompatActivity {
    ListView scoreList;

    //private int[] score = new int[0];

    Integer[] valuesInt = new Integer[]{12, 212, 21, 2323, 3232, 2121};
    StartQuestionActivity startQuestionActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_question);
        scoreList = findViewById(R.id.lv_scoreQuestion);


        startQuestionActivity = new StartQuestionActivity();
        Bundle bundle = getIntent().getExtras();
        // ArrayList<String> array = (ArrayList<String>) bundle.getStringArrayList("array_list");
        Intent mIntent = getIntent();
        ArrayList<Player> mUsers = mIntent.getParcelableArrayListExtra("UniqueKey");

        Log.i("dd", "onCreate:ssssssss "+mUsers);


        Arrays.sort(valuesInt, Collections.<Integer>reverseOrder());

        final ArrayList<String> list = new ArrayList<>();
        if (mIntent == null ) {
            mUsers = new ArrayList<>();
        }  else {
            for (Player aValuesInt : mUsers) {
                list.addAll(Collections.singleton(("" + aValuesInt)));
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        scoreList.setAdapter(adapter);
    }



    }
