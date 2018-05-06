package com.example.megas.calendar;

/**
 * Created by megas on 2018/04/27.
 */

public class TimeTableItem {
    private int timeTableID;
    private String title;
    private Date startDay;

    public TimeTableItem(int timeTableID, String title, Date startDay) {
        this.timeTableID = timeTableID;
        this.title = title;
        this.startDay = startDay;
    }

    public int getTimeTableID() {
        return timeTableID;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDay() {
        return startDay;
    }
}
