package com.example.megas.calendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class AddTimeTable extends AppCompatActivity implements View.OnClickListener {

    EditText edtTimeTableTitle;
    TextView edtTimeTableStartDay;
    TimeTableItem item;
    Button btnSave;
    Date date;
    TimeTableDBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time_table);

        edtTimeTableTitle = findViewById(R.id.edtTimeTableTitle);
        edtTimeTableStartDay = findViewById(R.id.txtTimeTableStartDay);
        btnSave = findViewById(R.id.btnTimeTableSave);
        database =new TimeTableDBHelper(this);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
        date = new com.example.megas.calendar.Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        edtTimeTableStartDay.setText(date.toShowString());

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTimeTableSave:
                item=new TimeTableItem(database.getNewId(),edtTimeTableTitle.getText().toString(),date);
                database.insert(item);

                setResult(1);
                finish();
                break;
        }
    }
}
