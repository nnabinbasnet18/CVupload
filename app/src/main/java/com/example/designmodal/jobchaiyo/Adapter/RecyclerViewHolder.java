package com.example.designmodal.jobchaiyo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.designmodal.jobchaiyo.R;

/**
 * Created by compware on 5/15/2018.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener

{
    TextView company_name,job_title,deadline;
    public RecyclerViewHolder(View itemView) {

        super(itemView);
        company_name = itemView.findViewById(R.id.company_Name);
        job_title = itemView.findViewById(R.id.job_title);
        deadline = itemView.findViewById(R.id.deadline);

    }

    @Override
    public void onClick(View view)
    {

    }
}