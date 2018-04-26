package com.example.megas.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by megas on 2018/04/01.
 */

public class MyListAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<Item> data;

    public MyListAdapter(ArrayList<Item> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new MyViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
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
