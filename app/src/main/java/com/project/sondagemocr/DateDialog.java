package com.project.sondagemocr;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Calendar;

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    EditText edtNascAluno;

    public void receiveView(View view){
        edtNascAluno=(EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this,year,month,day);

    }

    public void onDateSet(DatePicker view, int year, int month, int day){

        String date = day + "/" + (month+1) + "/" + year;
        edtNascAluno.setText(date);
    }
}
    /*
    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), listener, year, month, day);
        Field mDatePickerField;
        try { mDatePickerField = dialog.getClass().getDeclaredField("mDatePicker");
            mDatePickerField.setAccessible(true); } catch (Exception e) { e.printStackTrace();
        }
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        return dialog; }
        */
