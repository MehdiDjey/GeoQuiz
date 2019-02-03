package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    public void toStart(View view) {
        Intent startQuestion = new Intent(this, StartQuestionActivity.class);
        startActivity(startQuestion);
    }

    public void goToHighScore(View view) {
        Intent highScoreQuestion = new Intent(this, HighScoreQuestionActivity.class);
        startActivity(highScoreQuestion);
    }

    public void toExit(View view) {
        super.onBackPressed();
    }
}
