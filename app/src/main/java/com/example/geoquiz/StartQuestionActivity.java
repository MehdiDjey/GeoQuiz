package com.example.geoquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StartQuestionActivity extends AppCompatActivity {

    DataBaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);
        dbhelper = new DataBaseHelper(this);
    }

    public void onConfirm(View view) {
        QuestionFragment frag= (QuestionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        frag.setTextView(frag.getRandQuestion());
        frag.getRandResponse();

    }
}
