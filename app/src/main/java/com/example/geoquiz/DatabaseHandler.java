package com.example.geoquiz;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHandler {
    private static final String TAG = "DataBaseHandler";
    private static final String DB_NAME = "quizDB";
    private static final String TABLE_NAME = "informations";
    private static final String ID_ = "Id_";
    private static final String PAYS = "Pays";
    private static final String CAPITAL = "Capital";
    private static final String POPULATION = "Population";
    private static final String DEVISE = "Devise";
    // TODO: en cas d'echec changer les variable flag et monument en int pour recuperer les ref.
    private static final String MONUMENT = "Monumenent";
    private static final String FLAG = " Flag";
    private static final int DB_VERSION = 1;
    Context context;
    private SQLiteDatabase sqliteDB;
    private DataBaseHelper dbHelper;
    private Cursor mCur;
    private Cursor mCurRandom;

    public DatabaseHandler(Context context) {

        dbHelper = new DataBaseHelper(context);
        this.context = context;

    }

    // Open Database
    public void open(Context context) {
        sqliteDB = dbHelper.getWritableDatabase();
        Toast.makeText(context, "Create database " + dbHelper.getDatabaseName(), Toast.LENGTH_SHORT).show();

    }

    // Close Database
    public void close() {
        if (sqliteDB != null)
            sqliteDB.close();
    }


    public Cursor getInfo() {

        Cursor cursor = sqliteDB.query(TABLE_NAME, null, null, null, null, null,
                null);
        cursor.moveToFirst();
//		while(cursor.moveToNext())
//		{
//		Log.e("In getUserInfo()", "name:"+cursor.getString(1)+", age:"+cursor.getString(2));
//		}
        return cursor;
    }


    public Cursor getData() {
        try {
            String sql = "SELECT * FROM " + TABLE_NAME;

            mCur = sqliteDB.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            Log.i(TAG, "getData: " + mCur);
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }
/*    public long insertInfo(String pays, String capitale, String population , String devise, String monument, String flag)
    {
        ContentValues contentValues=new ContentValues();
        sqliteDB=dbHelper.getWritableDatabase();
        contentValues.put(PAYS, pays);
        contentValues.put(CAPITAL, capitale);
        contentValues.put(POPULATION, population);
        contentValues.put(DEVISE, devise);
        contentValues.put(MONUMENT, monument);
        contentValues.put(FLAG, flag);


        return  sqliteDB.insert(TABLE_NAME, null, contentValues);
    }*/


}
