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
    List<Task> taskList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = new DatabaseManager(this);

        taskList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewTasks);

        findViewById(R.id.fab_add_task).setOnClickListener(this);

//        TaskInfoAdapter infoAdapter = new TaskInfoAdapter(this, taskList);
//        ListView listView = findViewById(R.id.listViewTasks);
//        listView.setAdapter(infoAdapter);

        loadTasksFromDatabase();
    }

    private void loadTasksFromDatabase() {
        Cursor cursor = mDatabase.getAllTasks();

        if (cursor.moveToFirst()) {
            do {
                taskList.add(new Task(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                ));
            } while (cursor.moveToNext());

            TaskAdapter adapter = new TaskAdapter(this, R.layout.list_layout_task, taskList, mDatabase);
            listView.setAdapter(adapter);
        }
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
