package com.example.designmodal.jobchaiyo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designmodal.jobchaiyo.JobAttributes;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

/**
 * Created by compware on 5/15/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Context context;
    private List<JobAttributes> item;


    public RecyclerViewAdapter(Context context, List<JobAttributes> list) {
        this.context = context;
        this.item = list;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.jobs_feed, parent, false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(layoutView);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

            holder.company_name.setText(item.get(position).getArea_name());
            holder.job_title.setText(item.get(position).getJob_title());
            holder.deadline.setText(item.get(position).getDeadline());


    }

    @Override
    public int getItemCount() {
        return item.size();
    }
}
