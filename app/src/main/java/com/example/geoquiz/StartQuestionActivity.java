package com.example.geoquiz;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StartQuestionActivity extends AppCompatActivity {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final String QUESTION_PAYS = "Quel est le drapeau de ";
    private static final String QUESTION_POPULATION = "Quel est la population de";
    private static final String QUESTION_DEVISE = "Quel est la devise de";
    private static final String QUESTION_FLAG = "A quel pays appartient ce drapeau";
    private static final String QUESTION_MONUMENT = "Ou ce trouve ce monument";
    private static final String QUESTION_CAPITAL = "Qelle est la capitale de ";
    public TextView question_tv;
    DataBaseHelper dbHelper;
    Random randomQuestion = new Random();
    ArrayList<CountryInfo> questReponse;
    RadioGroup radioGroup;
    RadioButton reponse1, reponse2, reponse3, reponse4;
    int rowSize;
    String[] questions = {QUESTION_CAPITAL, QUESTION_DEVISE, QUESTION_PAYS, QUESTION_POPULATION, QUESTION_MONUMENT, QUESTION_FLAG};
    boolean question1;
    boolean question2;
    boolean question3;
    boolean question4;
    boolean question5;
    boolean question6;
    CountryInfo quest;
    CountryInfo ans1;
    CountryInfo ans2;
    CountryInfo ans3;
    CountryInfo reponseTo;
    TextView score_tv;
    TextView timer;
    int myScore=0;
    String correctAnswer;
    QuestionFragment frag;


    int score;
    String getReponse1, getReponse2, getReponse3, getReponse4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_question);
        dbHelper = new DataBaseHelper(this);
        timer= findViewById(R.id.timer_tv);
        score_tv = findViewById(R.id.tv_score_question);
        question_tv = findViewById(R.id.tv_question);
        rowSize = dbHelper.getAll().size();


        addListnerRadio();

        //addListnerRadio(view);
        getRandom();
        getRandQuestion();
        getRandResponse();

    }

    public void getRandQuestion() {
        String theQuestion;

        switch (randomQuestion.nextInt(questions.length)) {

            case 0: // Questions pour les capitales
                correctAnswer = quest.getCapitale();
                theQuestion = questions[0] + " " + quest.getPays();
                question_tv.setText(theQuestion);


                question1 = true;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 1://Question pour les devises
                correctAnswer = quest.getDevise();

                theQuestion = questions[1] + " " + quest.getPays();
                question_tv.setText(theQuestion);

                question1 = false;
                question2 = true;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;

                break;

            case 2: // Question les drapeaux
                correctAnswer = quest.getFlag();
                theQuestion = questions[2] + " " + quest.getPays();
                question_tv.setText(theQuestion);

                question1 = false;
                question2 = false;
                question3 = true;
                question4 = false;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 3: // Question pour le nombre de population
                correctAnswer = quest.getPopulation();
                theQuestion = questions[3] + " " + quest.getPays();
                question_tv.setText(theQuestion);

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = true;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 4:// Question pour les monument

                correctAnswer = quest.getMonument();
                theQuestion = questions[4] + " " + quest.getMonument();
                question_tv.setText(theQuestion);

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = true;
                question6 = false;
                //getRandIdMonument();
                break;

            case 5: // Question pour ls flag

                correctAnswer = quest.getPays();
                theQuestion = questions[5]+" "+quest.getFlag();
 /*               question_tv.setTransformationMethod(null);
                SpannableStringBuilder ssb = new SpannableStringBuilder(theQuestion);
                ssb.setSpan(new ImageSpan(context, getIdRessource(quest.getFlag()), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);*/
                question_tv.setText(theQuestion);

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = true;

                break;

            default:
                theQuestion = " NULL";
                reponse1.setText("NULLL");
                reponse2.setText("NULL");
                reponse3.setText("NULL");
                reponse4.setText("NULL");
                break;

        }
        Log.i("tag", "getRandQuestion: " + correctAnswer);

    }

    void getRandResponse() {

        if (question1 && noDuplicate()) {
            reponse1.setText(questReponse.get(0).getCapitale());
            reponse2.setText(questReponse.get(1).getCapitale());
            reponse3.setText(questReponse.get(2).getCapitale());
            reponse4.setText(questReponse.get(3).getCapitale());


        } else if (question2 && noDuplicate()) {
            reponse1.setText(questReponse.get(0).getDevise());
            reponse2.setText(questReponse.get(1).getDevise());
            reponse3.setText(questReponse.get(2).getDevise());
            reponse4.setText(questReponse.get(3).getDevise());

        } else if (question3 && noDuplicate()) {

 /*               reponse1.setButtonDrawable(GetImage(getContext(),questReponse.get(0).getFlag()));
                reponse2.setButtonDrawable(GetImage(getContext(),questReponse.get(1).getFlag()));
                reponse3.setButtonDrawable(GetImage(getContext(),questReponse.get(2).getFlag()));
                reponse4.setButtonDrawable(GetImage(getContext(),questReponse.get(3).getFlag()));*/
          /*      reponse1.setButtonDrawable(getIdRessource(questReponse.get(0).getFlag()));
                reponse2.setButtonDrawable(getIdRessource(questReponse.get(1).getFlag()));
                reponse3.setButtonDrawable(getIdRessource(questReponse.get(2).getFlag()));
                reponse4.setButtonDrawable(getIdRessource(questReponse.get(3).getFlag()));*/
/*
            String mDrawableName1 = questReponse.get(0).getFlag();
            String mDrawableName2 = questReponse.get(1).getFlag();
            String mDrawableName3 = questReponse.get(2).getFlag();
            String mDrawableName4 = questReponse.get(3).getFlag();
            int resID1 = getResources().getIdentifier(mDrawableName1 , "drawable", this.getClass().getName());
            int resID2 = getResources().getIdentifier(mDrawableName2 , "drawable", this.getClass().getName());
            int resID3 = getResources().getIdentifier(mDrawableName3 , "drawable", this.getClass().getName());
            int resID4 = getResources().getIdentifier(mDrawableName4 , "drawable", this.getClass().getName());

          reponse1.setButtonDrawable(resID1);
          reponse2.setButtonDrawable(resID2);
          reponse3.setButtonDrawable(resID3);
          reponse4.setButtonDrawable(resID4);
*/
            reponse1.setText(questReponse.get(0).getFlag());
            reponse2.setText(questReponse.get(1).getPays());
            reponse3.setText(questReponse.get(2).getFlag());
            reponse4.setText(questReponse.get(3).getFlag());



        } else if (question4 && noDuplicate()) {
            reponse1.setText(questReponse.get(0).getPopulation());
            reponse2.setText(questReponse.get(1).getPopulation());
            reponse3.setText(questReponse.get(2).getPopulation());
            reponse4.setText(questReponse.get(3).getPopulation());

        } else if (question5 && noDuplicate()) {
            reponse1.setText(questReponse.get(0).getMonument());
            reponse2.setText(questReponse.get(1).getMonument());
            reponse3.setText(questReponse.get(2).getMonument());
            reponse4.setText(questReponse.get(3).getMonument());

        } else if (question6 && noDuplicate()) {

            reponse1.setText(questReponse.get(0).getPays());
            reponse2.setText(questReponse.get(1).getPays());
            reponse3.setText(questReponse.get(2).getPays());
            reponse4.setText(questReponse.get(3).getPays());
        }

    }

    boolean noDuplicate() {
        return ans1.getId() != ans2.getId()
                & ans1.getId() != ans3.getId()
                & ans1.getId() != reponseTo.getId()
                & ans2.getId() != ans3.getId()
                & ans2.getId() != reponseTo.getId()
                & ans3.getId() != reponseTo.getId()
                && !ans1.equals(ans2)
                && !ans1.equals(ans3)
                && !ans1.equals(reponseTo)
                && !ans2.equals(ans3)
                && !ans2.equals(reponseTo)
                && !ans3.equals(reponseTo);
    }

    public void getRandom() {

        Random rnd = new Random();
        quest = dbHelper.getAll().get(rnd.nextInt(rowSize));
        ans1 = dbHelper.getAll().get(rnd.nextInt(rowSize));
        ans2 = dbHelper.getAll().get(rnd.nextInt(rowSize));
        ans3 = dbHelper.getAll().get(rnd.nextInt(rowSize));
        reponseTo = quest;

        questReponse = new ArrayList<>();
        questReponse.add(ans1);
        questReponse.add(ans2);
        questReponse.add(ans3);
        questReponse.add(reponseTo);

        Collections.shuffle(questReponse);
        Log.i("sd", "onCreateView: " + questReponse + " \n " + questReponse.size());

    }

    public void addListnerRadio() {

        radioGroup = findViewById(R.id.radioGroupe);
        reponse1 = findViewById(R.id.radioButton_reponse1);
        reponse2 = findViewById(R.id.radioButton_reponse2);
        reponse3 = findViewById(R.id.radioButton_reponse3);
        reponse4 = findViewById(R.id.radioButton_reponse4);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                reset();
                switch (checkedId) {

                    case R.id.radioButton_reponse1:
                        getReponse1 = reponse1.getText().toString();
                        if (getReponse1.equals(correctAnswer)) {
                            score++;
                            reponse1.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            reponse1.setTextColor(getResources().getColor(R.color.red));
                        }
                        Toast.makeText(StartQuestionActivity.this, reponse1.getText(), Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.radioButton_reponse2:
                        getReponse2 = reponse2.getText().toString();
                        if (getReponse2.equals(correctAnswer)) {
                            score++;
                            reponse2.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            reponse2.setTextColor(getResources().getColor(R.color.red));
                        }
                        Toast.makeText(StartQuestionActivity.this, reponse2.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton_reponse3:
                        getReponse3 = reponse3.getText().toString();
                        if (getReponse3.equals(correctAnswer)) {
                            score++;
                            reponse3.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            reponse3.setTextColor(getResources().getColor(R.color.red));
                        }
                        Toast.makeText(StartQuestionActivity.this, reponse3.getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton_reponse4:
                        getReponse4 = reponse4.getText().toString();
                        if (getReponse4.equals(correctAnswer)) {
                            score++;
                            reponse4.setTextColor(getResources().getColor(R.color.green));
                        } else {
                            reponse4.setTextColor(getResources().getColor(R.color.red));
                        }
                        Toast.makeText(StartQuestionActivity.this, reponse4.getText(), Toast.LENGTH_SHORT).show();
                        break;

                }

                Log.i("tag", "addListnerRadio: " + getReponse1 + " " + getReponse2 + "  " + getReponse3 + "  " + getReponse4);
                Log.i("tag", "addListnerRadio:Score  " + score);
                score_tv.setText(String.valueOf(score));
            }
        });
    }

    public  void reset() {
        reponse1.setChecked(false);
        reponse2.setChecked(false);
        reponse3.setChecked(false);
        reponse4.setChecked(false);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {

                getRandQuestion();
                getRandResponse();
                getRandom();
                reponse1.setTextColor(Color.BLACK);
                reponse2.setTextColor(Color.BLACK);
                reponse3.setTextColor(Color.BLACK);
                reponse4.setTextColor(Color.BLACK);
                reponse1.setButtonDrawable(null);
                reponse2.setButtonDrawable(null);
                reponse3.setButtonDrawable(null);
                reponse4.setButtonDrawable(null);
            }
        }, 2000);
    }
}
