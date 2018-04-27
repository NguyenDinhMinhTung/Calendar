package com.example.megas.calendar;

/**
 * Created by megas on 2018/04/27.
 */

public class TimeTableItem {
    private int id;
    private String title;
    private Date startDay;

    public TimeTableItem(int id, String title, Date startDay) {
        this.id = id;
        this.title = title;
        this.startDay = startDay;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDay() {
        return startDay;
    }
}
