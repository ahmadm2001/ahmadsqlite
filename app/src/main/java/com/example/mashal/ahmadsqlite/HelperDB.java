package com.example.mashal.ahmadsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDB extends SQLiteOpenHelper {
    String strCreate, strDelete;
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    public HelperDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public HelperDB( Context context) {
        super(context,DATABASE_NAME , null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        strCreate="CREATE TABLE "+ MyStudents.TABLE_MYSTUDENTS;
        strCreate+=" ("+ MyStudents.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+ MyStudents.NAME+" TEXT,";
        strCreate+=" "+ MyStudents.ID+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+StudentsClass.TABLE_STUDENTSCLASS;
        strCreate+=" ("+StudentsClass.KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+StudentsClass.TEACHERNAME+" TEXT,";
        strCreate+=" "+StudentsClass.GRADE+" INTEGER,";
        strCreate+=" "+StudentsClass.AGE+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        strDelete = "DROP TABLE IF EXISTS " + MyStudents.TABLE_MYSTUDENTS;
        db.execSQL(strDelete);
        strDelete = "DROP TABLE IF EXISTS " +StudentsClass.TABLE_STUDENTSCLASS;
        db.execSQL(strDelete);
        onCreate(db);
    }
}