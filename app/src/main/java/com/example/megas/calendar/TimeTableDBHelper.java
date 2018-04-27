package com.example.megas.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by megas on 2018/04/01.
 */

public class TimeTableDBHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "TIMETABLELIST";
    public static final String DATABASE_NAME = "LESSON";
    public static final String DB_ID = "id";
    public static final String DB_STARTDAY = "start_day";
    public static final String DB_TITLE = "title";

    public TimeTableDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" + DB_ID + " INTEGER, " + DB_TITLE + " TEXT, " + DB_STARTDAY + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insert(int id, String title, Date startDay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DB_TITLE, title);
        contentValues.put(DB_ID, id);
        contentValues.put(DB_STARTDAY, startDay.toString());

        db.insert(TABLE_NAME, null, contentValues);

        db.close();

        return true;
    }

    public boolean insert(TimeTableItem item) {
        return insert(item.getId(), item.getTitle(), item.getStartDay());
    }

    public ArrayList<TimeTableItem> getData() {
        ArrayList<TimeTableItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " order by " + DB_STARTDAY, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            int ID = res.getInt(res.getColumnIndex(DB_ID));
            String title = res.getString(res.getColumnIndex(DB_TITLE));
            Date startDay = new Date(res.getString(res.getColumnIndex(DB_STARTDAY)));

            list.add(new TimeTableItem(ID, title, startDay));

            res.moveToNext();
        }

        return list;
    }

    public void delete(TimeTableItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("DATA", DB_ID + "= ?", new String[]{Integer.toString(item.getId())});
        db.close();
    }

    public int getNewId() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " order by " + DB_ID + " DESC", null);
        res.moveToFirst();

        int ID = res.getInt(res.getColumnIndex(DB_ID));
        return ID + 1;
    }
}
