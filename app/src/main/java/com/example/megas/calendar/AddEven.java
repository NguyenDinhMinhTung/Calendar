package com.example.megas.calendar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Calendar;
import java.util.TimeZone;

public class AddEven extends AppCompatActivity implements View.OnClickListener {
    TextView txtDate, txtStartTime, txtEndTime;
    EditText edtTitle, edtNote;
    Button btnSave, btnContinue;
    Item oldItem, newItem;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    android.support.v7.widget.Toolbar tolInput;
    TabHost host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_even2);

        tolInput=findViewById(R.id.toolBarInput);
        setSupportActionBar(tolInput);
        getSupportActionBar().setTitle("イベント追加");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        host=findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec tab1=host.newTabSpec("tab1");
        tab1.setIndicator("tab1");
        tab1.setContent(R.id.tab1);
        host.addTab(tab1);

        TabHost.TabSpec tab2=host.newTabSpec("tab2");
        tab2.setIndicator("tab2");
        tab2.setContent(R.id.tab2);
        host.addTab(tab2);

        txtDate = findViewById(R.id.txtDate);
        txtStartTime = findViewById(R.id.txtStartTime);
        txtEndTime = findViewById(R.id.txtEndTime);
        edtTitle = findViewById(R.id.edtTitle);
        edtNote = findViewById(R.id.edtNote);
        btnSave = findViewById(R.id.btnSave);
        btnContinue = findViewById(R.id.btnContinue);
        datePickerDialog = new DatePickerDialog();
        timePickerDialog = new TimePickerDialog();

        Intent intent = getIntent();

        if (intent.getIntExtra("hasData", 0) == 1) {
            btnContinue.setVisibility(View.GONE);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnSave.getLayoutParams();
            params.weight = 15f;

            btnSave.setLayoutParams(params);

            oldItem = (Item) intent.getSerializableExtra("item");
            newItem = oldItem.clone();
        } else {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
            com.example.megas.calendar.Date date = new com.example.megas.calendar.Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            Time timeStart = new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
            Time timeEnd = new Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

            newItem = new Item("", date, "", timeStart, timeEnd);
        }

        txtDate.setText(newItem.getDate().toShowString());
        txtStartTime.setText(newItem.getStartTime().toShowString());
        txtEndTime.setText(newItem.getEndTime().toShowString());
        edtTitle.setText(newItem.getTitle());
        edtNote.setText(newItem.getNote());

        btnSave.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        txtStartTime.setOnClickListener(this);
        txtEndTime.setOnClickListener(this);
        txtDate.setOnClickListener(this);

        datePickerDialog.setOnClickListener(new DatePickerDialog.OnClickListener() {
            @Override
            public void onClick(int year, int monthOfYear, int dayOfMonth) {
                newItem.setDate(new com.example.megas.calendar.Date(year, monthOfYear, dayOfMonth));
                txtDate.setText(newItem.getDate().toShowString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSave:
                if (edtTitle.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "タイトルを入力してください", Toast.LENGTH_SHORT).show();
                } else {
                    newItem.setTitle(edtTitle.getText().toString());
                    newItem.setNote(edtNote.getText().toString());

                    Intent intent = new Intent();
                    intent.putExtra("item", newItem);
                    intent.putExtra("oldItem",oldItem);
                    setResult(124, intent);
                    finish();
                }
                break;

            case R.id.btnContinue:

                break;

            case R.id.txtStartTime:
                timePickerDialog.setOnClickListener(new TimePickerDialog.OnClickListener() {
                    @Override
                    public void onClick(int hour, int minute) {
                        Time time =new Time(hour,minute);

                        if (txtEndTime.getText().toString().compareTo(time.toShowString()) < 0) {
                            newItem.setEndTime(time.clone());
                            txtEndTime.setText(newItem.getEndTime().toShowString());
                        }

                        newItem.setStartTime(time);
                        txtStartTime.setText(newItem.getStartTime().toShowString());
                    }
                });
                timePickerDialog.setNow(newItem.getStartTime());
                timePickerDialog.show(getFragmentManager(), "");
                break;

            case R.id.txtEndTime:
                timePickerDialog.setOnClickListener(new TimePickerDialog.OnClickListener() {
                    @Override
                    public void onClick(int hour, int minute) {
                        Time time = new Time(hour, minute);

                        if (txtStartTime.getText().toString().compareTo(time.toShowString()) > 0) {
                            newItem.setStartTime(time.clone());
                            txtStartTime.setText(newItem.getStartTime().toShowString());
                        }

                        newItem.setEndTime(time);
                        txtEndTime.setText(newItem.getEndTime().toShowString());
                    }
                });
                timePickerDialog.setNow(newItem.getEndTime());
                timePickerDialog.show(getFragmentManager(), "");
                break;

            case R.id.txtDate:
                datePickerDialog.setNow(newItem.getDate());
                datePickerDialog.show(getFragmentManager(), "");
        }
    }
}
