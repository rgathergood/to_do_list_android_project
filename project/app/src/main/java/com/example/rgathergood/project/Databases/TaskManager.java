package com.example.rgathergood.project.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.rgathergood.project.Models.Task;

import java.util.ArrayList;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class TaskManager extends DatabaseHelper {

    static final String TABLE_NAME = "TodoList";
    static final String KEY_ID = "id";
    static final String TASK_NAME = "task";
    static final String TASK_DESCRIPTION = "description";
    static final String DUE_DATE = "date";
    static final String PRIORITY = "priority";
    private static final String[] COLUMNS = {KEY_ID, TASK_NAME, PRIORITY};

    public TaskManager(Context context) {
        super(context);
    }


    public ArrayList<Task> selectAllDesc() {

        SQLiteDatabase mDatabase = this.getReadableDatabase();
        String sortOrder = KEY_ID + " DESC";
        Cursor cursor = mDatabase.query(TABLE_NAME, null, null, null, null, null, sortOrder);
        ArrayList<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow(KEY_ID));
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(TASK_NAME));
            String description = cursor.getString(
                    cursor.getColumnIndexOrThrow(TASK_DESCRIPTION));
            String deadlineDate = cursor.getString(
                    cursor.getColumnIndexOrThrow(DUE_DATE));
            String priority = cursor.getString(
                    cursor.getColumnIndexOrThrow(PRIORITY));

            Task task = new Task(id, name, description, deadlineDate, priority);
            tasks.add(task);
        }
        return tasks;
    }

        public boolean addTask(String name, String description, String deadlineDate, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, name);
        contentValues.put(TASK_DESCRIPTION, description);
        contentValues.put(DUE_DATE, deadlineDate);
        contentValues.put(PRIORITY, priority);

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }

    public boolean updateTask(int id, String name, String description, String deadlineDate, String priority) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, name);
        contentValues.put(TASK_DESCRIPTION, description);
        contentValues.put(DUE_DATE, deadlineDate);
        contentValues.put(PRIORITY, priority);
        return sqLiteDatabase.update(TABLE_NAME, contentValues, KEY_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public boolean deleteTask(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, KEY_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }
}
