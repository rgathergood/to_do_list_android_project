package com.example.rgathergood.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rgathergood.project.Databases.DatabaseManager;
import com.example.rgathergood.project.Models.Task;

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
        textViewDateAdded.setText(task.getDate());

        Button button = convertView.findViewById(R.id.button_complete);

        button.setTag(task);

        convertView.setTag(task);

        return convertView;
    }

}
