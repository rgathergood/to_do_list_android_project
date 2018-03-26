package com.example.rgathergood.project.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rgathergood.project.DatabaseManager;
import com.example.rgathergood.project.R;
import com.example.rgathergood.project.Task;
import com.example.rgathergood.project.TaskAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseManager mDatabase;
    ArrayList<Task> taskList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseManager(this);

        listView = findViewById(R.id.listViewTasks);

        findViewById(R.id.fab_add_task).setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add_task:
                startActivity(new Intent(this, TaskAddActivity.class));
                break;
        }
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
}
