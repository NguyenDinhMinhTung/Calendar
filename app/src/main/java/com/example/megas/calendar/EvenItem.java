package com.example.megas.calendar;

import java.io.Serializable;

/**
 * Created by megas on 2018/04/01.
 */

public class EvenItem implements Serializable {
    private String title, note;
    private Time startTime, endTime;
    private com.example.megas.calendar.Date date;

    public EvenItem(String title, com.example.megas.calendar.Date date, String note, Time startTime, Time endTime) {
        this.title = title;
        this.note = note;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
    }

    @Override
    protected EvenItem clone() {
        return new EvenItem(this.title,this.date,this.note,this.startTime,this.endTime);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public void setDate(com.example.megas.calendar.Date date) {
        this.date = date;
    }

    public com.example.megas.calendar.Date getDate() {
        return date;
    }
}
