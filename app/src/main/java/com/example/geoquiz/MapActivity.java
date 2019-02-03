package com.example.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void toExit(View view) {
        super.onBackPressed();
    }

    public void mapHighScore(View view) {
    }

    public void mapQuestion(View view) {
        Intent mapQuestion = new Intent(this,MapQuestion.class);
        startActivity(mapQuestion);
    }
}
