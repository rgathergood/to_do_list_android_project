package com.example.rgathergood.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class TaskInfoActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("task");

        TextView textViewName = findViewById(R.id.textViewTaskName);
        textViewName.setText(task.getName());
        TextView textViewDescription = findViewById(R.id.textViewTaskDescription);
        textViewDescription.setText(task.getDescription());
        TextView textViewDateAdded = findViewById(R.id.textViewDateAdded);
        textViewDateAdded.setText(task.getDateAdded());
    }
}
