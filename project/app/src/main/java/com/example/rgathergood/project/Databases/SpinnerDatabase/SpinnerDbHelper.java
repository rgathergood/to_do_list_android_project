package com.example.rgathergood.project.Databases.SpinnerDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rgathergood.project.Models.Priorities;
import com.example.rgathergood.project.Models.Priorities;

import java.util.ArrayList;

/**
 * Created by rgathergood on 27/03/2018.
 */

public class SpinnerDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "spindb";
    public static final String TABLE_NAME = "priorities";
    public static final int DATABASE_VERSION = 1;

    public SpinnerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void seed() {
        for(Priorities priorities : Priorities.values()) {
            save(priorities);
        }
        //for priority : priorty.values
        // save(proritoy.name(), priority.getValue()
       // save()
    }

    private void save(Priorities priorities) {
        String name = priorities.name();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL UNIQUE)";
        db.execSQL(CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DELETE);
        onCreate(db);
    }
}
