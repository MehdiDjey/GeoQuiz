package com.example.geoquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HighScoreQuestionActivity extends AppCompatActivity {
    ListView scoreList;

    //private int[] score = new int[0];

    Integer[] valuesInt = new Integer[]{12, 212, 21, 2323, 3232, 2121};
    ArrayList<? extends String> storedata ;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_question);

        scoreList = findViewById(R.id.lv_scoreQuestion);

        storedata = getIntent().getParcelableArrayListExtra("key");
        Log.i("dsd", "onCreate: testou "+storedata);
        //getIntent().getParcelableArrayListExtra("key");


        Arrays.sort(valuesInt, Collections.<Integer>reverseOrder());

        final ArrayList<String> list = new ArrayList<>();

        for (Integer aValuesInt : valuesInt) {
            list.addAll(Collections.singleton("" + aValuesInt));
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        scoreList.setAdapter(adapter);
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<>();

        StableArrayAdapter(Context context, int textViewResourceId,
                           List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
