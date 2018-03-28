package com.example.rgathergood.project.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rgathergood.project.Databases.DatabaseManager;
import com.example.rgathergood.project.R;
import com.example.rgathergood.project.Models.Task;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskInfoActivity extends AppCompatActivity implements Serializable, DatePickerDialog.OnDateSetListener {

    private Calendar calendar;

    DatabaseManager mDatabase;
    TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_info);

        mDatabase = new DatabaseManager(this);
        calendar = Calendar.getInstance();

        Intent intent = getIntent();
        final Task task = (Task) intent.getSerializableExtra("task");

        TextView textViewName = findViewById(R.id.textViewTaskName);
        textViewName.setText(task.getName());
        TextView textViewDescription = findViewById(R.id.textViewTaskDescription);
        textViewDescription.setText(task.getDescription());
        TextView textViewDateAdded = findViewById(R.id.textViewDateAdded);
        textViewDateAdded.setText(task.getDate());

        findViewById(R.id.button_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTask(task);
            }
        });
    }

    public void showDatePickerDialogUpdate(View view) {
        MainActivity.DatePickerFragment datePickerFragment = new MainActivity.DatePickerFragment();
        datePickerFragment.onDateSetListener = this;
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
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
        dateView = view.findViewById(R.id.textViewUpdateDate);
        final Spinner spinner = view.findViewById(R.id.spinner_update_priority);

        editTextName.setText(task.getName());
        editTextDescription.setText(task.getDescription());
        dateView.setText(task.getDate());

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

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String deadlineDateString = simpleDateFormat.format(calendar.getTime());

                if (mDatabase.updateTask(task.getId(), name, description, deadlineDateString, priority)) {
                    Toast.makeText(TaskInfoActivity.this, "Task Updated!", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                    TaskInfoActivity.this.finish();
                } else {
                    Toast.makeText(TaskInfoActivity.this, "Task Not Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment {

        public static DatePickerDialog.OnDateSetListener onDateSetListener;

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
        }
    }
}
