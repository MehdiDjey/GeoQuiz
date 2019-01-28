package com.example.geoquiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

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
    Random randomPays = new Random();
    Random randomPopulation = new Random();
    Random randomDevise = new Random();
    Random randomCapitale = new Random();
    Random randomFlag = new Random();
    Random randomMonument = new Random();
    RadioButton reponse1, reponse2, reponse3, reponse4, aucune;
    int rowSize = 0;
    String[] questions = {QUESTION_CAPITAL, QUESTION_DEVISE, QUESTION_PAYS, QUESTION_POPULATION, QUESTION_MONUMENT, QUESTION_FLAG};
    boolean question1;
    boolean question2;
    boolean question3;
    boolean question4;
    boolean question5;
    boolean question6;
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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        question_tv = view.findViewById(R.id.tv_question);

        rowSize = dbHelper.getRowCount();

        reponse1 = view.findViewById(R.id.radioButton_reponse1);
        reponse2 = view.findViewById(R.id.radioButton_reponse2);
        reponse3 = view.findViewById(R.id.radioButton_reponse3);
        reponse4 = view.findViewById(R.id.radioButton_reponse4);
        aucune = view.findViewById(R.id.radioButton_aucune);

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
                theQuestion = questions[0] + " " + getRandPays();

                question1 = true;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;
                break;
            case 1:
                theQuestion = questions[1] + " " + getRandPays();

                question1 = false;
                question2 = true;
                question3 = false;
                question4 = false;
                question5 = false;
                question6 = false;
                break;
            case 2:
                theQuestion = questions[2] + " " + getRandPays();

                question1 = false;
                question2 = false;
                question3 = true;
                question4 = false;
                question5 = false;
                question6 = false;
                break;
            case 3:
                theQuestion = questions[3] + " " + getRandPays();

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = true;
                question5 = false;
                question6 = false;
                break;
            case 4:
                theQuestion = questions[4] + " " + getRandMonument();

                question1 = false;
                question2 = false;
                question3 = false;
                question4 = false;
                question5 = true;
                question6 = false;
                break;
            case 5:
                theQuestion = questions[5] + " " + getRandFlag();

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

        return theQuestion.toUpperCase();
    }

    void getRandResponse() {

        if (question1) {
            reponse1.setText(getRandCapitale());
            reponse2.setText(getRandCapitale());
            reponse3.setText(getRandCapitale());
            reponse4.setText(getRandCapitale());
        } else if (question2) {
            reponse1.setText(getRandDevise());
            reponse2.setText(getRandDevise());
            reponse3.setText(getRandDevise());
            reponse4.setText(getRandDevise());

        } else if (question3) {
            reponse1.setText("PAYS&");
            reponse2.setText("PAYQ2");
            reponse3.setText("PAYS3");
            reponse4.setText("PAYS4");
        } else if (question4) {
            reponse1.setText(getRandPopulation());
            reponse2.setText(getRandPopulation());
            reponse3.setText(getRandPopulation());
            reponse4.setText(getRandPopulation());

        } else if (question5) {
            reponse1.setText(getRandMonument());
            reponse2.setText(getRandMonument());
            reponse3.setText(getRandMonument());
            reponse4.setText(getRandMonument());

        } else if (question6) {
            reponse1.setText(getRandFlag());
            reponse2.setText(getRandFlag());
            reponse3.setText(getRandFlag());
            reponse4.setText(getRandFlag());

        }

    }


    String getRandPays() {

        return dbHelper.getAll().get(randomPays.nextInt(rowSize)).getPays().toUpperCase();
    }

    String getRandCapitale() {

        return dbHelper.getAll().get(randomCapitale.nextInt(rowSize)).getCapitale().toUpperCase();
    }

    String getRandDevise() {

        return dbHelper.getAll().get(randomDevise.nextInt(rowSize)).getDevise().toUpperCase();
    }

    String getRandPopulation() {
        return dbHelper.getAll().get(randomPopulation.nextInt(rowSize)).getPopulation().toUpperCase();
    }

    String getRandFlag() {

        return dbHelper.getAll().get(randomFlag.nextInt(rowSize)).getFlag().toUpperCase();
    }

    String getRandMonument() {
        return dbHelper.getAll().get(randomMonument.nextInt(rowSize)).getMonument();
    }

    public void setTextView(String text) {
        question_tv.setText(text);
    }

    public void setTextToRadio(String reponseOne, String reponseTwo, String reponseThree, String reponseFour) {
        reponse1.setText(reponseOne);
        reponse2.setText(reponseTwo);
        reponse3.setText(reponseThree);
        reponse4.setText(reponseFour);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
