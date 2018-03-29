package com.example.rgathergood.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.rgathergood.project.Databases.TaskManager;
import com.example.rgathergood.project.Models.Task;

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

        textViewName.setText(task.getName());
        textViewDateAdded.setText(task.getDate());

        Button button = convertView.findViewById(R.id.button_complete);

        button.setTag(task);

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