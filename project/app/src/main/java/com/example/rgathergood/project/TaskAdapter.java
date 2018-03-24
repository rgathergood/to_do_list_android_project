package com.example.rgathergood.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

//      view.findViewById(R.id.taskInfo)

        return view;
    }
}
