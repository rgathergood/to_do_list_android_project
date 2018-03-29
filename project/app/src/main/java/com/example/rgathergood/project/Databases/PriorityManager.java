package com.example.rgathergood.project.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by rgathergood on 27/03/2018.
 */

public class PriorityManager extends DatabaseHelper {
    public static final String TABLE_NAME = "priorities";
    public static final String KEY_ID = "id";
    public static final String PRIORITY = "priority";

    public PriorityManager(Context context) {
        super(context);
    }

    public String[] getAll() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> priorityList = new ArrayList<>();
        String[] allPriorities = new String[0];
        if (cursor.moveToFirst()) {
            do {
                String priority = cursor.getString(cursor.getColumnIndexOrThrow("priority"));
                priorityList.add(priority);
            } while (cursor.moveToNext());
            cursor.close();

            allPriorities = new String[priorityList.size()];
            allPriorities = priorityList.toArray(allPriorities);
            db.close();
        }
        return allPriorities;
    }
}

