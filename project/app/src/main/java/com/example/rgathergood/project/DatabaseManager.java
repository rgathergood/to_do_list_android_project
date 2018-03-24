package com.example.rgathergood.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TaskDatabase";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE_ADDED = "date";
    public static final String COLUMN_COMPLETED = "completed";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = ("CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT tasks_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_DESCRIPTION + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_DATE_ADDED + " datetime NOT NULL,\n" +
                "    " + COLUMN_COMPLETED + " varchar(200) NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    Cursor getAllTasks() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean addTask(String name, String description, String dateAdded, String completed) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_DATE_ADDED, dateAdded);
        contentValues.put(COLUMN_COMPLETED, completed);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;


    }
}
