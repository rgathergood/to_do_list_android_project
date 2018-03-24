package com.example.rgathergood.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskAddActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseManager mDatabase;

    EditText editTextName;
    EditText editTextDescription;
    Spinner spinnerPriority;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);

        mDatabase = new DatabaseManager(this);

        editTextName = (EditText) findViewById(R.id.task_name_add);
        editTextDescription = (EditText) findViewById(R.id.task_description_add);
        spinnerPriority = (Spinner) findViewById(R.id.spinner_priority);

        findViewById(R.id.buttonAddTask).setOnClickListener(this);
    }

    private void addTask() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String priority = spinnerPriority.getSelectedItem().toString();

        if (name.isEmpty()) {
            editTextName.setError("Name can't be empty!");
            editTextName.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            editTextDescription.setError("Description can't be empty1");
            editTextDescription.requestFocus();
            return;
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateAdded = simpleDateFormat.format(calendar.getTime());

        if (mDatabase.addTask(name, description, dateAdded, priority))
            Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Task Not Added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddTask:
                addTask();
                break;
        }
    }
}
