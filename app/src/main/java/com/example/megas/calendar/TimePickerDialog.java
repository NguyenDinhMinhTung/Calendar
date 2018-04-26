package com.example.megas.calendar;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by megas on 2018/04/18.
 */

public class TimePickerDialog extends DialogFragment {
    OnClickListener onClickListener;
    Time now;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setNow(Time time) {
        now = time;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar cal = Calendar.getInstance();

        return new android.app.TimePickerDialog(getActivity(), new android.app.TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                onClickListener.onClick(i, i1);
            }
        }, now.getHour(), now.getMinute(), true);
    }

    public interface OnClickListener {
        void onClick(int hour, int minute);
    }
}
