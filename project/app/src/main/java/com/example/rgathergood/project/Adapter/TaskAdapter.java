package com.example.rgathergood.project.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rgathergood.project.Models.Task;
import com.example.rgathergood.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rgathergood on 24/03/2018.
 */

public class TaskAdapter extends BaseAdapter {

    Context context;
    List<Task> taskList;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Task getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.context).inflate(R.layout.list_layout_task, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textViewTaskName);
        TextView textViewDateAdded = convertView.findViewById(R.id.textViewDateAdded);
        Button button = convertView.findViewById(R.id.button_complete);
        ImageView priorityRag = convertView.findViewById(R.id.priorityRag);

        textViewName.setText(task.getName());
        textViewDateAdded.setText(task.getDate());
        button.setTag(task);
        priorityRag.setTag(task);

        if (task.getPriority().equals("Low")) {
            priorityRag.setImageResource(R.color.colorYellow);
            Log.d("Priority", String.valueOf(task.getPriority()));
        } else if (task.getPriority().equals("Medium")) {
            priorityRag.setImageResource(R.color.colorAccent);
        } else if (task.getPriority().equals("High")) {
            priorityRag.setImageResource(R.color.colorPrimaryDark);
        }

        convertView.setTag(task);

        return convertView;
    }

    public void filterTasks(String priority) {
        List<Task> filteredTask = new ArrayList<>();

        for (Task task : taskList) {
            if (task.getPriority().equals(priority)) {
                filteredTask.add(task);
            }
        }

        this.taskList = filteredTask;

        this.notifyDataSetChanged();
    }
}