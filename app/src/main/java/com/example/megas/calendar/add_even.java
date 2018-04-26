package com.example.megas.calendar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class add_even extends AppCompatActivity {

   /* Button btnCancel, btnOK,btnSelectStart,btnSelectEnd;
    EditText txtStartTime,txtEndTime,txtTitle,txtNote,txtDate;
    com.example.megas.calendar.Time timeStart=new Time(),timeEnd=new Time(),time_=null;
    com.example.megas.calendar.Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_even);

        btnCancel=findViewById(R.id.btnCancel);
        btnOK=findViewById(R.id.btnOK);
        btnSelectStart=findViewById(R.id.btnSelectStart);
        btnSelectEnd=findViewById(R.id.btnSelectEnd);
        txtStartTime=findViewById(R.id.txtStartTime);
        txtEndTime=findViewById(R.id.txtEndTime);
        txtTitle=findViewById(R.id.edtTitle);
        txtNote=findViewById(R.id.edtNote);
        txtDate=findViewById(R.id.edtDate);

        txtDate.setKeyListener(null);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog();
                datePickerDialog.setOnClickListener(new DatePickerDialog.OnClickListener() {
                    @Override
                    public void onClick(int year, int monthOfYear, int dayOfMonth) {
                        date=new com.example.megas.calendar.Date(year,monthOfYear,dayOfMonth);
                        txtDate.setText(date.toString());
                    }
                });
                datePickerDialog.show(getFragmentManager(),"");
            }
        });

        Intent intent=getIntent();

        if (intent.getIntExtra("hasData",0)==1) {
            txtTitle.setText(intent.getStringExtra("title"));
            txtNote.setText(intent.getStringExtra("note"));

            Time startTime= (Time) intent.getSerializableExtra("startTime");
            Time endTime=(Time) intent.getSerializableExtra("endTime");

            timeStart=startTime;
            timeEnd=endTime;

            txtStartTime.setText(startTime.toString());
            txtEndTime.setText(endTime.toString());
        }


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSelectStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSelectEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();

                intent.putExtra("date",date);
                intent.putExtra("timeStart",timeStart);
                intent.putExtra("timeEnd",timeEnd);
                intent.putExtra("title",txtTitle.getText().toString());
                intent.putExtra("note",txtNote.getText().toString());

                setResult(124,intent);
                finish();
            }
        });
    } */
}
