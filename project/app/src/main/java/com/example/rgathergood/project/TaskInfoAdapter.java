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

public class TaskInfoAdapter extends ArrayAdapter<Task> {

    public TaskInfoAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        Task currentTask = getItem(position);

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_task_info, parent, false);
        }

            TextView textViewName = listItemView.findViewById(R.id.textViewTaskName);
            textViewName.setText(currentTask.getName());
            TextView textViewDescription = listItemView.findViewById(R.id.textViewTaskDescription);
            textViewDescription.setText(currentTask.getDescription());
            TextView textViewDateAdded = listItemView.findViewById(R.id.textViewDateAdded);
            textViewDateAdded.setText(currentTask.getDateAdded());
//        Spinner spinnerPriority = listItemView.findViewById(R.id.spinner_priority);
//        spinnerPriority.setAdapter();

            listItemView.setTag(currentTask);
            return listItemView;

    }
}