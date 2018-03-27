package com.example.rgathergood.project.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rgathergood.project.Models.Task;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TaskDatabase";
    public static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DEADLINE_DATE = "deadlineDate";
    public static final String COLUMN_PRIORITY = "priority";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = ("CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT tasks_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_DESCRIPTION + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_DEADLINE_DATE + " DATE NOT NULL,\n" +
                "    " + COLUMN_PRIORITY + " varchar(200) NOT NULL\n" +
                ");");

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Task> selectAllDesc() {

        SQLiteDatabase mDatabase = this.getReadableDatabase();
        String sortOrder = COLUMN_ID + " DESC";
        Cursor cursor = mDatabase.query(TABLE_NAME, null, null, null, null, null, sortOrder);
        ArrayList<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(COLUMN_ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_NAME));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION));
            String deadlineDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_DEADLINE_DATE));
            String priority = cursor.getString(
                    cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));

            Task task = new Task(id, name, description, deadlineDate, priority);
            tasks.add(task);
        }
        return tasks;
    }

    public boolean addTask(String name, String description, String deadlineDate, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_DEADLINE_DATE, deadlineDate);
        contentValues.put(COLUMN_PRIORITY, priority);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public boolean updateTask(int id, String name, String description, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_PRIORITY, priority);
        return sqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean deleteTask(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}
