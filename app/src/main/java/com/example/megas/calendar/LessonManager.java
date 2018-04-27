package com.example.megas.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class LessonManager extends AppCompatActivity {
    RecyclerView recyclerView;
    TimeTableDBHelper database;
    MainListAdapter adapter;
    ArrayList<TimeTableItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_manager);

        database=new TimeTableDBHelper(this);
        recyclerView=findViewById(R.id.lstTimeTable);

        list = database.getData();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MainListAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
