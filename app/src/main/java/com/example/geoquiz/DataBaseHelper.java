package com.example.geoquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DataBaseHelper";
    private static final String TABLE_NAME = "informations";
    private static final String DB_NAME = "quizDB";
    private static final String ID_ = "Id_";
    private static final String PAYS = "Pays";
    private static final String CAPITAL = "Capital";
    private static final String POPULATION = "Population";
    private static final String DEVISE = "Devise";
    // TODO: en cas d'echec changer les variable flag et monument en int pour recuperer les ref.
    private static final String MONUMENT = "Monumenent";
    private static final String FLAG = " Flag";
    private static final String CREATE_TABLE_QUIZ = "create table " + TABLE_NAME
            + "("
            + ID_ + " integer primary key autoincrement, "
            + PAYS + " text not null, "
            + CAPITAL + " text not null, "
            + POPULATION + " text not null ,"
            + DEVISE + " text not null, "
            + MONUMENT + " text, "
            + FLAG + " text);";


    private static final int DB_VERSION = 1;
    private String[] columns = {ID_, PAYS, CAPITAL, POPULATION, DEVISE, MONUMENT, FLAG};

    private Context context;

    private ArrayList<CountryInfo> country;

    DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUIZ);

        Log.i(TAG, "onCreate: Invoked ");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_NAME + "'");
        onCreate(db);

    }

    public void onDelete() {
        context.deleteDatabase(DB_NAME);
    }

    void insertInfo(CountryInfo country) {
        ContentValues contentValues = new ContentValues();
        //SQLiteDatabase db =this.getWritableDatabase();
        SQLiteDatabase db = this.getReadableDatabase();
        contentValues.put(columns[1], country.getPays().trim());
        contentValues.put(columns[2], country.getCapitale().trim());
        contentValues.put(columns[3], country.getPopulation().trim());
        contentValues.put(columns[4], country.getDevise().trim());
        contentValues.put(columns[5], country.getMonument().trim());
        contentValues.put(columns[6], country.getFlag().trim());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }

    ArrayList<CountryInfo> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT DISTINCT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        country = new ArrayList<>();
        CountryInfo countryInfo;

        if (cursor.moveToFirst()) {
            do {
                //cursor.moveToNext();
                countryInfo = new CountryInfo();
                countryInfo.setId(cursor.getInt(0));
                countryInfo.setPays(cursor.getString(1).trim());
                countryInfo.setCapitale(cursor.getString(2).trim());
                countryInfo.setPopulation(cursor.getString(3).trim());
                countryInfo.setDevise(cursor.getString(4).trim());
                countryInfo.setMonument(cursor.getString(5).trim());
                countryInfo.setFlag(cursor.getString(6).trim());
                country.add(countryInfo);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return country;
    }
}
