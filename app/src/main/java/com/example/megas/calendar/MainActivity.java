package com.example.megas.calendar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton floatingActionButton;
    DBHelper database;
    ArrayList<Item> list;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Button btnBack, btnForward;
    Date now;
    TextView txtNow;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        recyclerView = findViewById(R.id.recycleView);
        btnBack = findViewById(R.id.btnBack);
        btnForward = findViewById(R.id.btnForward);
        txtNow = findViewById(R.id.txtNow);

        btnBack.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        txtNow.setOnClickListener(this);

        datePickerDialog = new DatePickerDialog();
        datePickerDialog.setOnClickListener(new DatePickerDialog.OnClickListener() {
            @Override
            public void onClick(int year, int monthOfYear, int dayOfMonth) {
                now = new Date(year, monthOfYear, dayOfMonth);
                txtNow.setText(now.toShowString());
                refreshList(database, recyclerView, list, adapter, now);
            }
        });

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        now = new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        txtNow.setText(now.toShowString());
        database = new DBHelper(this);

        list = database.getData(now);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        adapter = new MyListAdapter(list);
        recyclerView.setAdapter(adapter);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEven.class);
                startActivityForResult(intent, 123);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

                if (childView != null && e.getAction() == MotionEvent.ACTION_UP) {
                    int pos = recyclerView.getChildPosition(childView);
                    Intent intent = new Intent(MainActivity.this, AddEven.class);
                    Item item = list.get(pos);

                    intent.putExtra("hasData", 1);
                    intent.putExtra("item", item);

                    startActivityForResult(intent, 125);
                }
                return true;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    public void refreshList(DBHelper database, RecyclerView recyclerView, ArrayList<Item> list, RecyclerView.Adapter adapter, Date now) {
        list = database.getData(now);
        adapter = new MyListAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 124) {
            Item item = (Item) data.getSerializableExtra("item");

            if (requestCode == 125) {
                Item oldItem = (Item) data.getSerializableExtra("oldItem");
                database.delete(oldItem);
            }

            database.insert(item);

            refreshList(database, recyclerView, list, adapter, now);
        }
    }

    public Date getDate(Date now, int i) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        calendar.set(now.getYear(), now.getMonth(), now.getDay());
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return new Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mnuLessonManager:
            {
                Intent intent=new Intent(this,LessonManager.class);
                startActivity(intent);

                break;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                now = getDate(now, -1);
                txtNow.setText(now.toShowString());
                refreshList(database, recyclerView, list, adapter, now);
                break;

            case R.id.btnForward:
                now = getDate(now, 1);
                txtNow.setText(now.toShowString());
                refreshList(database, recyclerView, list, adapter, now);
                break;

            case R.id.txtNow:
                datePickerDialog.setNow(now);
                datePickerDialog.show(getFragmentManager(),"");
                break;
        }
    }
}
