package com.example.rgathergood.project.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.rgathergood.project.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public static DatePickerDialog.OnDateSetListener onDateSetListener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView tv1= (TextView) getActivity().findViewById(R.id.textViewDate);
        tv1.setText("Completed by: "+view.getDayOfMonth()+"/"+view.getMonth()+"/"+view.getYear());
    }
}
