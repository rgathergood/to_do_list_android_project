package com.example.rgathergood.project.Models;

import java.io.Serializable;

/**
 * Created by rgathergood on 23/03/2018.
 */

public class Task implements Serializable {
    int id;
    String name;
    String description;
    String date;
    String completed;

    public Task(int id, String name, String description, String date, String completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
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
