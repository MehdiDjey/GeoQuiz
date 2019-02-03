package com.example.geoquiz;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {
    private static final String TAG = "MySharedPreference";
    // Sharedpref file name
    private static final String PREF_NAME = "pref";
    private static final String SCORES = "scores";
    // Shared pref mode
    int PRIVATE_MODE = 0;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    // Context
    private Context _context;

    public MySharedPreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void saveHighScoreList(String scoreString) {
        editor.putString(SCORES, scoreString);
        editor.commit();
        editor.apply();
    }

    public String getHighScoreList() {
        return pref.getString(SCORES, "");
    }
}
