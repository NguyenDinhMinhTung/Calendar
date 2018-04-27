package com.example.megas.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by megas on 2018/04/01.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = Calendar.getInstance().getTime().getYear() + "" + Calendar.getInstance().getTime().getMonth();
    public static final String DB_DATE = "date";
    public static final String DB_TITLE = "title";
    public static final String START_TIME = "start_time";
    public static final String END_TIME = "end_time";
    public static final String DB_NOTE = "note";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DATA (" + DB_TITLE + " TEXT, " + DB_DATE + " TEXT, " + START_TIME + " TEXT, " + END_TIME + " TEXT, " + DB_NOTE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DATA");
        onCreate(sqLiteDatabase);
    }

    public boolean insert(String title, Date date, Time start, Time end, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB_TITLE, title);
        contentValues.put(DB_DATE, date.toString());
        contentValues.put(START_TIME, start.toString());
        contentValues.put(END_TIME, end.toString());
        contentValues.put(DB_NOTE, note);

        db.insert("DATA", null, contentValues);

        db.close();

        return true;
    }

    public boolean insert(EvenItem evenItem) {
        return insert(evenItem.getTitle(), evenItem.getDate(), evenItem.getStartTime(), evenItem.getEndTime(), evenItem.getNote());
    }

    public ArrayList<EvenItem> getData(Date now) {
        ArrayList<EvenItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from DATA where " + DB_DATE + "= " + now.toString() + " order by " + START_TIME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String title = res.getString(res.getColumnIndex(DB_TITLE));
            String note = res.getString(res.getColumnIndex(DB_NOTE));
            Date date = new Date(res.getString(res.getColumnIndex(DB_DATE)));
            Time startTime = new Time(res.getString(res.getColumnIndex(START_TIME)));
            Time endTime = new Time(res.getString(res.getColumnIndex(END_TIME)));

            list.add(new EvenItem(title, date, note, startTime, endTime));

            res.moveToNext();
        }

        return list;
    }

    public ArrayList<EvenItem> getData() {
        ArrayList<EvenItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from DATA order by " + START_TIME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            String title = res.getString(res.getColumnIndex(DB_TITLE));
            String note = res.getString(res.getColumnIndex(DB_NOTE));
            Date date = new Date(res.getString(res.getColumnIndex(DB_DATE)));
            Time startTime = new Time(res.getString(res.getColumnIndex(START_TIME)));
            Time endTime = new Time(res.getString(res.getColumnIndex(END_TIME)));

            list.add(new EvenItem(title, date, note, startTime, endTime));

            res.moveToNext();
        }

        return list;
    }

    public void delete(Date date, Time startTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DATA", DB_DATE + "=? AND " + START_TIME + "=?",
                new String[]{date.toString(), startTime.toString()});
        db.close();
    }

    public void delete(EvenItem evenItem) {
        delete(evenItem.getDate(), evenItem.getStartTime());
    }
}
