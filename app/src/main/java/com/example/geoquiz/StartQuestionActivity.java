package com.example.geoquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartQuestionActivity extends AppCompatActivity {

    DataBaseHelper dbhelper;
    TextView score;
    int myScore=0;
    QuestionFragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);
        dbhelper = new DataBaseHelper(this);

        score = findViewById(R.id.tv_score_question);
         frag= (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
    }

    public void onConfirm(View view) {

        frag.setTextView(frag.getRandQuestion());
        frag.getRandResponse();
        frag.getRandom();
        if (frag.checkRepone()) {
            myScore++;
            score.setText(""+myScore);
        }


    }
}
