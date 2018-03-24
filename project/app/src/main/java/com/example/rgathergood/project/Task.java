package com.example.rgathergood.project;

/**
 * Created by rgathergood on 23/03/2018.
 */

public class Task {
    int id;
    String name;
    String description;
    String dateAdded;
    String completed;

    public Task(int id, String name, String description, String dateAdded, String completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateAdded = dateAdded;
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

    public String getDateAdded() {
        return dateAdded;
    }

    public String isCompleted() {
        return completed;
    }

}
