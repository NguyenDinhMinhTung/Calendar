package com.example.megas.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by megas on 2018/04/01.
 */

public class TimeTableListAdapter extends RecyclerView.Adapter<TimeTableViewHolder> {

    private ArrayList<TimeTableItem> data;

    public TimeTableListAdapter(ArrayList<TimeTableItem> data) {
        this.data = data;
    }

    @Override
    public TimeTableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item,parent,false);
        return new TimeTableViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TimeTableViewHolder holder, int position) {
        holder.txtStartTime.setText(data.get(position).getStartTime().getHour()+":"+data.get(position).getStartTime().getMinute()+"~");
        holder.txtEndTime.setText(data.get(position).getEndTime().getHour()+":"+data.get(position).getEndTime().getMinute());
        holder.txtTitle.setText(data.get(position).getTitle());
        holder.txtNote.setText(data.get(position).getNote());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

class TimeTableViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView txtStartTime,txtEndTime,txtTitle,txtNote;

    public TimeTableViewHolder(View itemView) {
        super(itemView);
        this.view=itemView;
        this.txtTitle=view.findViewById(R.id.txtTitle);
        this.txtStartTime=view.findViewById(R.id.txtStartTime);
        this.txtEndTime=view.findViewById(R.id.txtEndTime);
        this.txtNote=view.findViewById(R.id.txtNote);
    }
}
