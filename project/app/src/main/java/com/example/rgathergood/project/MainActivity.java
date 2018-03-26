package com.example.rgathergood.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
