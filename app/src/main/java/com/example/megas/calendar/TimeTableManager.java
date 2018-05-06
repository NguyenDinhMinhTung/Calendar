package com.example.megas.calendar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class TimeTableManager extends AppCompatActivity {
    RecyclerView recyclerView;
    TimeTableDBHelper database;
    TimeTableListAdapter adapter;
    ArrayList<TimeTableItem> list;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_manager);

        database = new TimeTableDBHelper(this);
        recyclerView = findViewById(R.id.lstTimeTable);
        floatingActionButton = findViewById(R.id.btnFabTimeTableManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TimeTableManager.this, AddTimeTable.class);
                startActivityForResult(intent,0);
            }
        });


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        refreshList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0&& resultCode==1){
            refreshList();
        }
    }

    public void refreshList(){
        list = database.getData();
        adapter = new TimeTableListAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
