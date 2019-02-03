package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class HighScoreQuestionActivity extends AppCompatActivity {
    private static final String TAG = "HighScoreQuestionactivity";
    ListView scoreList;
    ArrayList<String> list;
    ArrayList<Player> mUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_question);
        scoreList = findViewById(R.id.lv_scoreQuestion);
        list = new ArrayList<>();
        Intent mIntent = getIntent();
        mUsers = mIntent.getParcelableArrayListExtra("UniqueKey");
        //Arrays.sort(mUsers, Collections.<Integer>reverseOrder());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, list);

        if (mUsers == null) {
            mUsers = new ArrayList<>();
        }
        for (Player mUser : mUsers) {
            list.addAll(Collections.singleton(String.valueOf(mUser)));
            Log.i("d", "onCreate: " + list);
        }

        scoreList.setAdapter(adapter);
    }


}
