package com.example.geoquiz;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static String TAG = " myTag";

    DataBaseHelper dbHelper;
    SQLiteDatabase sqliteDB;
    DatabaseHandler dbHalnder;

    ListAdapter listAdapter;
    ArrayList<HashMap<String, String>> myList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.i(TAG, "onCreate: du Main");
        dbHelper = new DataBaseHelper(this);
        sqliteDB = dbHelper.getWritableDatabase();

        loadCSV2();
        // dbHelper.getCapitale();
        //dbHelper.getId();
        //dbHelper.getPays();
        //dbHelper.getDevise();
        dbHelper.getAll();


    }


    public void loadCSV2() {

        String mCSVfile = "data.csv";

        AssetManager manager = this.getAssets();
        InputStream inStream = null;
        try {
            Log.i(TAG, "loadCSV2: ");
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            Log.i(TAG, "loadCSV2:  ERROR");
            e.printStackTrace();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line;
        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");

                Log.i("CSVParser", "Skipping Bad CSV Row");
                Log.i("CSVParser", "" + colums[1]);
                CountryInfo countryInfo = new CountryInfo();
       /*         ContentValues cv = new ContentValues();
                cv.put(dbHelper.columns[0], colums[0]);
                cv.put(dbHelper.columns[1], colums[1]);
                cv.put(dbHelper.columns[2], colums[2]);
                cv.put(dbHelper.columns[3], colums[3]);
                cv.put(dbHelper.columns[4], colums[4]);
                cv.put(dbHelper.columns[5], colums[5]);
                cv.put(dbHelper.columns[6], colums[6]);
                //cv.put(dbHelper.columns[6], colums[6].trim());

                sqliteDB.insert(DataBaseHelper.TABLE_NAME, null, cv);*/

                String pays = colums[0];
                String capitale = colums[1];
                String popuation = colums[2];
                String devise = colums[3];
                String monument = colums[4];
                String flag = colums[5];

                countryInfo.setPays(pays);
                countryInfo.setCapitale(capitale);
                countryInfo.setPopulation(popuation);
                countryInfo.setDevise(devise);
                countryInfo.setMonument(monument);
                countryInfo.setFlag(flag);
                //dbHelper.insertInfo(colums[0],colums[1],colums[2],colums[3],colums[4],colums[5]);
                dbHelper.insertInfo(countryInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void goToQuestions(View view) {
        Intent questionActivity = new Intent(this, QuestionActivity.class);
        startActivity(questionActivity);
    }

    public void gotToMap(View view) {
    }

    public void toExit(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application ?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
