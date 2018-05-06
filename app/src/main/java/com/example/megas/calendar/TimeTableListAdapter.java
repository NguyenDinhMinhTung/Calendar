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
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.time_table_list_item,parent,false);
        return new TimeTableViewHolder(v);
    }


    @Override
    public void onBindViewHolder(TimeTableViewHolder holder, int position) {
        holder.txtTitle.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

class TimeTableViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView txtTitle;

    public TimeTableViewHolder(View itemView) {
        super(itemView);

        this.view=itemView;
        this.txtTitle=view.findViewById(R.id.txtTitle);
    }
}
