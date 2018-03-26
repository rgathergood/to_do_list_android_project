package com.example.rgathergood.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class TaskAdapter extends ArrayAdapter<Task>{

    Context context;
    int layoutRes;
    List<Task> taskList;
    DatabaseManager mDatabase;

    public TaskAdapter(Context context, int layoutRes, List<Task> taskList, DatabaseManager mDatabase) {
        super(context, layoutRes, taskList);

        this.context = context;
        this.layoutRes = layoutRes;
        this.taskList = taskList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutRes, null);

        TextView textViewName = view.findViewById(R.id.textViewTaskName);
        TextView textViewDateAdded = view.findViewById(R.id.textViewDateAdded);
        //completed spinner here??

        final Task task = taskList.get(position);

        textViewName.setText(task.getName());
        textViewDateAdded.setText(task.getDateAdded());

        view.findViewById(R.id.button_complete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTask(task);
            }
        });

        view.setTag(task);

        return view;
    }

    private void deleteTask(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Are you sure this is Complete? The task will be deleted.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mDatabase.deleteTask(task.getId()))
                    Toast.makeText(context, "Task completed! Well done!", Toast.LENGTH_SHORT).show();

                reloadTasksFromDatabase();
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

    public void reloadTasksFromDatabase() {
        Cursor cursor = mDatabase.getAllTasks();

        if (cursor.moveToFirst()) {
            taskList.clear();
            do {
                taskList.add(new Task(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                ));
            } while (cursor.moveToNext());

            notifyDataSetChanged();
        }
    }
}
