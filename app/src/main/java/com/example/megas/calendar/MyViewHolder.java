package com.example.megas.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by megas on 2018/04/01.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    View view;
    TextView txtStartTime,txtEndTime,txtTitle,txtNote;

    public MyViewHolder(View itemView) {
        super(itemView);
        this.view=itemView;
        this.txtTitle=view.findViewById(R.id.txtTitle);
        this.txtStartTime=view.findViewById(R.id.txtStartTime);
        this.txtEndTime=view.findViewById(R.id.txtEndTime);
        this.txtNote=view.findViewById(R.id.txtNote);
    }
}
