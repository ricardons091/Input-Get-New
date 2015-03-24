package com.example.arturdn.notes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by arturdn on 17/03/2015.
 */
public class DBProxy extends SQLiteOpenHelper{

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Notes.db";
    public static final String DB_TABLE_NAME = "Notes";
    public static final String DB_COL_ID = "id";
    public static final String DB_COL_TITLE = "title";
    public static final String DB_COL_NOTE = "note";

    public DBProxy(Context context){

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL("CREATE TABLE "+DB_TABLE_NAME+" ( "+DB_COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+DB_COL_TITLE+" TEXT, "+DB_COL_NOTE+" TEXT )");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public void insertar(String title, String body){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.putNull(DB_COL_ID);
        values.put(DB_COL_TITLE, title);
        values.put(DB_COL_NOTE, body);

        db.insert(DB_TABLE_NAME, null, values);
        db.close();

    }

    public void actualitzar(int id, String title, String body){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COL_TITLE, title);
        values.put(DB_COL_NOTE, body);

        db.update(DB_TABLE_NAME, values, DB_COL_ID + "=" + id, null);
        db.close();

    }


        public void notesperid(int Id, Infonota in){
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery =  "SELECT  " +
                    DB_COL_ID + "," +
                    DB_COL_TITLE + "," +
                    DB_COL_NOTE +
                    " FROM " + DB_TABLE_NAME
                    + " WHERE " +
                    DB_COL_ID + "=" + Id;


            Cursor cursor = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list

            if (cursor.moveToFirst()) {
                do {
                    in.id =cursor.getInt(cursor.getColumnIndex(DB_COL_ID));
                    in.title =cursor.getString(cursor.getColumnIndex(DB_COL_TITLE));
                    in.body  =cursor.getString(cursor.getColumnIndex(DB_COL_NOTE));

                } while (cursor.moveToNext());
            }
            if (Id != in.id){ //Parche
                in.title = "";
                in.body = "";
            }

            cursor.close();
            db.close();
        }


}
