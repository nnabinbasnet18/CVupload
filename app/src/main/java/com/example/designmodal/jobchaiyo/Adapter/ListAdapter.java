package com.example.designmodal.jobchaiyo.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.designmodal.jobchaiyo.JobAttributes;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<JobAttributes>
{

        private static final String LOG_TAG = ListAdapter.class.getSimpleName();


        public ListAdapter(Activity context, List<JobAttributes> androidFlavors)
        {
            super(context, 0, androidFlavors);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
            JobAttributes jobAttributes = getItem(position);

            // Adapters recycle views to AdapterViews.
            // If this is a new View object we're getting, then inflate the layout.
            // If not, this view already has the layout inflated from a previous call to getView,
            // and we modify the View widgets as usual.
            if (convertView == null)
            {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.jobs_feed, parent, false);
            }

            TextView company_name = (TextView) convertView.findViewById(R.id.company_Name);
            company_name.setText(jobAttributes.getArea_name());

            TextView job_title = (TextView) convertView.findViewById(R.id.job_title);
            job_title.setText(jobAttributes.getJob_title());
            TextView deadline = (TextView) convertView.findViewById(R.id.deadline);
            deadline.setText(jobAttributes.getDeadline());
            return convertView;
        }
    }
