package com.example.megas.calendar;

import java.io.Serializable;

/**
 * Created by megas on 2018/04/07.
 */

public class Date implements Serializable {
    private int year, month, day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Date(String date) {
        this.year = Integer.parseInt(date.substring(0, 4));
        this.month = Integer.parseInt(date.substring(4, 6));
        this.day = Integer.parseInt(date.substring(6, 8));
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String toShowString() {
        return String.format("%04d年%02d月%02d日", year, month, day);
    }

    @Override
    public String toString() {
        return String.format("%04d%02d%02d", year, month, day);
    }
}
