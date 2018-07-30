package com.barry.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bynn on 2018/1/31.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    public static final String SQLITE_NAME = "people.db";
    public static final int SQLITE_VERSION = 1;



    public MyOpenHelper(Context context) {
        super(context, SQLITE_NAME, null, SQLITE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name char(10), phone char(20), money integer(10))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table handsome(_id integer primary key autoincrement, name char(20), phone char(20))");
    }
}
