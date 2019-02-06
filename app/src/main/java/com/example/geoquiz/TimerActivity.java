package com.example.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimerActivity extends AppCompatActivity {
    private int TIMER = 5970;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(TIMER);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(TimerActivity.this, StartQuestionActivity.class));
                }
            }
        };
        timer.start();
    }
}
