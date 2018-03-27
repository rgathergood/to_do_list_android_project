package com.example.rgathergood.project.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rgathergood.project.DatabaseManager;
import com.example.rgathergood.project.R;
import com.example.rgathergood.project.Task;

import java.io.Serializable;

public class TaskInfoActivity extends AppCompatActivity implements Serializable {

    DatabaseManager mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        mDatabase = new DatabaseManager(this);

        Intent intent = getIntent();
        final Task task = (Task) intent.getSerializableExtra("task");

        TextView textViewName = findViewById(R.id.textViewTaskName);
        textViewName.setText(task.getName());
        TextView textViewDescription = findViewById(R.id.textViewTaskDescription);
        textViewDescription.setText(task.getDescription());
        TextView textViewDateAdded = findViewById(R.id.textViewDateAdded);
        textViewDateAdded.setText(task.getDateAdded());

        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask(task);
            }
        });
    }

    private void updateTask(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_update_task, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextName = view.findViewById(R.id.editTextUpdateName);
        final EditText editTextDescription = view.findViewById(R.id.editTextUpdateDescription);
        final Spinner spinner = view.findViewById(R.id.spinner_update_priority);

        editTextName.setText(task.getName());
        editTextDescription.setText(task.getDescription());

        view.findViewById(R.id.button_update_task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                String priority = spinner.getSelectedItem().toString().trim();

                if (name.isEmpty()) {
                    editTextName.setError("Name can't be empty!");
                    editTextName.requestFocus();
                    return;
                }

                if (description.isEmpty()) {
                    editTextDescription.setError("Description can't be empty!");
                    editTextDescription.requestFocus();
                    return;
                }

                if (mDatabase.updateTask(task.getId(), name, description, priority)) {
                    Toast.makeText(TaskInfoActivity.this, "Task Updated!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    TaskInfoActivity.this.finish();
                } else {
                    Toast.makeText(TaskInfoActivity.this, "Task Not Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
