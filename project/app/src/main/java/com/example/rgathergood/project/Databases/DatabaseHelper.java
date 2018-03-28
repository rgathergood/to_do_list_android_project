package com.example.rgathergood.project.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rgathergood on 28/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ToDoList";
    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createPrioritiesTable = "create table " + PriorityManager.TABLE_NAME + " ( " +
                PriorityManager.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PriorityManager.PRIORITY + " STRING );";

        sqLiteDatabase.execSQL(createPrioritiesTable);

        sqLiteDatabase.execSQL("INSERT INTO " + PriorityManager.TABLE_NAME + " ( " + PriorityManager.PRIORITY + " ) " + " VALUES ('Low')");
        sqLiteDatabase.execSQL("INSERT INTO " + PriorityManager.TABLE_NAME + " ( " + PriorityManager.PRIORITY + " ) " + " VALUES ('Medium')");
        sqLiteDatabase.execSQL("INSERT INTO " + PriorityManager.TABLE_NAME + " ( " + PriorityManager.PRIORITY + " ) " + " VALUES ('High')");

        String createTaskTable = "create table "
                + TaskManager.TABLE_NAME + " ("
                + TaskManager.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TaskManager.TASK_NAME + " TEXT NOT NULL, "
                + TaskManager.TASK_DESCRIPTION + " TEXT NOT NULL, "
                + TaskManager.DUE_DATE + " TEXT NOT NULL, "
                + TaskManager.PRIORITY + " INTEGER, "
                + " FOREIGN KEY ("+TaskManager.PRIORITY+") REFERENCES "+ PriorityManager.TABLE_NAME+"("+ PriorityManager.KEY_ID+"));";
        sqLiteDatabase.execSQL(createTaskTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TaskManager.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PriorityManager.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
