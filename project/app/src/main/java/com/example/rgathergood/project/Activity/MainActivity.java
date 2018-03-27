package com.example.rgathergood.project.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rgathergood.project.Databases.DatabaseManager;
import com.example.rgathergood.project.R;
import com.example.rgathergood.project.Models.Task;
import com.example.rgathergood.project.TaskAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Date date = new Date();

    DatabaseManager mDatabase;
    ArrayList<Task> taskList;
    ListView listView;
    TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseManager(this);

        listView = findViewById(R.id.listViewTasks);

        dateView = findViewById(R.id.textViewDate);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    public void refreshList() {
        taskList = mDatabase.selectAllDesc();

        TaskAdapter taskAdapter = new TaskAdapter(this, taskList);

        listView.setAdapter(taskAdapter);
    }

//    public String setTodaysDate(Date date) {  // Pass in a java.util.date, get out a string of "yyyy/MM/dd"
//        SimpleDateFormat dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");
//        return dateSQLformat.format(date);
//    }
    //Do I need this?

    public void showDatePickerDialog(View view) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.onDateSetListener = this;
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String monthStr = "";
        String dayStr = "";
        month += 1;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = String.valueOf(month);
        }
        if (day < 10) {
            dayStr = "0" + day;
        } else {
            dayStr = String.valueOf(day);
        }

        TextView dateView = (TextView) findViewById(R.id.textViewDate);
        dateView.setText("Complete by: " + dayStr + "/" + monthStr + "/" + year);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void onListItemClick(View listItem) {
        Task task = (Task) listItem.getTag();
        Log.d("mainActivity", task.getName());
        Intent intent = new Intent(this, TaskInfoActivity.class);
        intent.putExtra("task", task);
        startActivity(intent);
    }

    public void onClickDelete(View view) {
        Task task = (Task) view.getTag();
        deleteTask(task);
    }

    public void deleteTask(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Are you sure this is Complete? The task will be deleted.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mDatabase.deleteTask(task.getId())) {
                    refreshList();
                    Toast.makeText(MainActivity.this, "Task completed! Well done!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void onClickAdd(View view) {
        addTask();
    }

    public void addTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_add_task, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextName = view.findViewById(R.id.task_name_add);
        final EditText editTextDescription = view.findViewById(R.id.task_description_add);
        final TextView editDeadlineDate = view.findViewById(R.id.textViewDate);
        final Spinner spinner = view.findViewById(R.id.spinner_priority);

        view.findViewById(R.id.buttonAddTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                Date deadlineDate = new Date(dateView.getText().toString());
                //problem here
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
                String deadlineDateString = simpleDateFormat.format(date);

                if (mDatabase.addTask(name, description, deadlineDateString, priority)) {
                    Toast.makeText(MainActivity.this, "Task Added", Toast.LENGTH_LONG).show();
                    refreshList();
                    alertDialog.dismiss();
                } else {
                    Toast.makeText(MainActivity.this, "Task Not Added", Toast.LENGTH_LONG).show();
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
