package com.example.megas.calendar;

import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by megas on 2018/04/01.
 */

public class Time implements Serializable {
    int hour, minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String time) {
        this.hour = Integer.parseInt(time.substring(0, 2));
        this.minute = Integer.parseInt(time.substring(2, 4));
    }

    public Time() {
        this.hour = 0;
        this.minute = 0;
    }

    public Time clone() {
        return new Time(this.hour, this.minute);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String toShowString() {
        return String.format("%02d:%02d", hour, minute);
    }

    @Override
    public String toString() {
        return String.format("%02d%02d", hour, minute);
    }
}
