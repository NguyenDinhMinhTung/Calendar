package com.example.megas.calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by megas on 2018/04/09.
 */

public class DatePickerDialog extends DialogFragment {

    OnClickListener onClickListener;
    Date now;

    public void setOnClickListener(OnClickListener onClickListener)
    {
        this.onClickListener=onClickListener;
    }

    public void setNow(Date now)
    {
        this.now=now;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar cal=Calendar.getInstance();
        return new android.app.DatePickerDialog(getActivity(), new android.app.DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    onClickListener.onClick(year,monthOfYear,dayOfMonth);
            }
        },
        now.getYear(), now.getMonth(),now.getDay());
    }

    public interface OnClickListener{
        void onClick(int year, int monthOfYear, int dayOfMonth);
    }
}
