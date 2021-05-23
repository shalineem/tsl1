package com.sign.language;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Amol Jagtap on 3/1/2018.
 */
public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "women", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String query = "CREATE TABLE womendata(username text,password text)";
            String query1 = "CREATE TABLE request(address text,date text,image blob,depa text)";

            db.execSQL(query);
            db.execSQL(query1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRow(String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String q = "DELETE FROM request WHERE date='" + date + "'";
        sqLiteDatabase.execSQL(q);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addData(String user, String password) throws Exception {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", user);
            contentValues.put("password", password);
            sqLiteDatabase.insert("womendata", null, contentValues);
            System.out.println("---------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRequest(String address, String date, byte[] image, String depa) throws Exception {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("address", address);
            contentValues.put("date", date);
            contentValues.put("image", image);
            contentValues.put("depa", depa);
            sqLiteDatabase.insert("request", null, contentValues);
            System.out.println("---------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletedata() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM womendata");
        sqLiteDatabase.execSQL("DELETE FROM request");
    }

    public ArrayList<String> getData() {
        ArrayList<String> arrayList = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String qury = "select * from womendata";
        // sqLiteDatabase.execSQL(qury);
        Cursor c = sqLiteDatabase.rawQuery(qury, null);
        if (c.moveToFirst()) {
            do {
                // Passing values
                arrayList.add(c.getString(0));
                arrayList.add(c.getString(1));

            } while (c.moveToNext());
        }
        return arrayList;
    }
}
