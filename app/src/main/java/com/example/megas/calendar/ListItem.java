package com.example.megas.calendar;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by megas on 2018/04/01.
 */

public class ListItem {
    String title,note;
    Time startTime,endTime;
    com.example.megas.calendar.Date date;

    public ListItem(String title, com.example.megas.calendar.Date date, String note, Time startTime, Time endTime) {
        this.title = title;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date=date;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public com.example.megas.calendar.Date getDate() {return date;}
}
