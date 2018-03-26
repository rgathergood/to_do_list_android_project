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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class TaskAdapter extends ArrayAdapter<Task>{

    Context context;
    List<Task> taskList;
    DatabaseManager mDatabase;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        super(context, 0, taskList);
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout_task, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewTaskName);
        TextView textViewDateAdded = convertView.findViewById(R.id.textViewDateAdded);

        textViewName.setText(task.getName());
        textViewDateAdded.setText(task.getDateAdded());

        convertView.setTag(task);

        return convertView;
    }


    private void deleteTask(final Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Are you sure this is Complete? The task will be deleted.");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (mDatabase.deleteTask(task.getId()))
                    Toast.makeText(context, "Task completed! Well done!", Toast.LENGTH_SHORT).show();
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
