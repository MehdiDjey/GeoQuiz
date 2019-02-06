package com.example.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        setupWindowAnimations();
    }
    private void setupWindowAnimations() {
        Explode fade = new Explode();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Explode slide = new Explode();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }
    public void toStart(View view) {
        Intent startQuestion = new Intent(this, TimerActivity.class);
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
