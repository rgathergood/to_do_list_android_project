package com.example.rgathergood.project.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rgathergood on 23/03/2018.
 */

public class Task implements Serializable {
    int id;
    String name;
    String description;
    String date;
    String completed;
    private static SimpleDateFormat dateSQLformat;

    public Task(int id, String name, String description, String date, String completed) {
//        dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
//        this.date = dateSQLformat.format(date);
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String isCompleted() {
        return completed;
    }

}
