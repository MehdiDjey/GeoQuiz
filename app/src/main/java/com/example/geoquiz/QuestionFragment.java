package com.example.geoquiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private static final String QUESTION_PAYS = "Quel est ce pays";
    private static final String QUESTION_POPULATION = "Quel est la population de";
    private static final String QUESTION_DEVISE = "Quel est la devise de";
    private static final String QUESTION_FLAG = "Quel est ce drapeau";
    private static final String QUESTION_MONUMENT = "Ou ce trouve ce monument";
    private static final String QUESTION_CAPITAL = "Qelle est la capitale de ";
    private static String REPONSE_1;
    private static String REPONSE_2;
    private static String REPONSE_3;
    private static String REPONSE_4;
    private static String AUCUNE;
    public TextView question_tv;
    DataBaseHelper dbHelper;
    Random randomQuestion = new Random();

    ArrayList<CountryInfo> questReponse;
    RadioGroup radioGroup;
    RadioButton reponse1, reponse2, reponse3, reponse4, aucune;
    int rowSize;
    String[] questions = {QUESTION_CAPITAL, QUESTION_DEVISE, QUESTION_PAYS, QUESTION_POPULATION, QUESTION_MONUMENT, QUESTION_FLAG};
    boolean question1;
    boolean question2;
    boolean question3;
    boolean question4;
    boolean question5;
    boolean question6;
    ArrayList<CountryInfo> arrayOfList;
    int idReponse1, idReponse2, idReponse3, idReponse4, idQuestion;
    CountryInfo quest;
    CountryInfo ans1;
    CountryInfo ans2;
    CountryInfo ans3;
    CountryInfo reponseTo;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private QuestionActivity questionActivity;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dbHelper = new DataBaseHelper(getActivity());
        final View view = inflater.inflate(R.layout.fragment_question, container, false);
        question_tv = view.findViewById(R.id.tv_question);
        //QuestionReponse questionReponse = new QuestionReponse();


        rowSize = dbHelper.getAll().size();


        reponse1 = view.findViewById(R.id.radioButton_reponse1);
        reponse2 = view.findViewById(R.id.radioButton_reponse2);
        reponse3 = view.findViewById(R.id.radioButton_reponse3);
        reponse4 = view.findViewById(R.id.radioButton_reponse4);
        aucune = view.findViewById(R.id.radioButton_aucune);

        getRandom();

        question_tv.setText(getRandQuestion());

        getRandResponse();


        // Inflate the layout for this fragment
        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof QuestionActivity) {
            this.questionActivity = (QuestionActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    String getRandQuestion() {
        String theQuestion;

        switch (randomQuestion.nextInt(questions.length)) {

            case 0:
                theQuestion = questions[0] + " " + quest.getPays();


                question1 = true;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 1:


                theQuestion = questions[1] + " " + quest.getPays();

                question1 = false;
                question2 = true;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 2:
                theQuestion = questions[2] + " " + quest.getPays();
                reponse1.setText("PAYS&");
                reponse2.setText("PAYQ2");
                reponse3.setText("PAYS3");
                reponse4.setText("PAYS4");
                question1 = false;
                question2 = false;
                question3 = true;
                question4 = false;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 3:

                theQuestion = questions[3] + " " + quest.getPays();

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = true;
                question5 = false;
                question6 = false;
                //getRandIdPays();
                break;

            case 4:


                theQuestion = questions[4] + " " + quest.getMonument();

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = true;
                question6 = false;
                //getRandIdMonument();
                break;

            case 5:


                theQuestion = questions[5] + " " + quest.getFlag();

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = true;
                // getRandIdFlag();
                break;

            default:
                theQuestion = " NULL";
                reponse1.setText("NULLL");
                reponse2.setText("NULL");
                reponse3.setText("NULL");
                reponse4.setText("NULL");
                break;

        }

        return theQuestion;
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
            reponse1.setText("PAYS&");
            reponse2.setText("PAYQ2");
            reponse3.setText("PAYS3");
            reponse4.setText("PAYS4");

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
            reponse1.setText(questReponse.get(0).getFlag());
            reponse2.setText(questReponse.get(1).getFlag());
            reponse3.setText(questReponse.get(2).getFlag());
            reponse4.setText(questReponse.get(3).getFlag());
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

    public void setTextView(String text) {
        question_tv.setText(text);
    }

    // TODO: 28/01/19
    public boolean checkRepone() {
        Log.i("dsds", "checkRepone: " + idQuestion + "  " + idReponse1 + "   " + idReponse2 + "  " + idReponse3 + "   " + idReponse4);
        if (idQuestion == idReponse1) {
            return true;
        } else if (idQuestion == idReponse2) {
            return true;
        } else if (idQuestion == idReponse3) {
            return true;
        } else return idQuestion == idReponse4;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
