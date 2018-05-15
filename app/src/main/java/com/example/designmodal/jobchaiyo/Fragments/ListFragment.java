package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.designmodal.jobchaiyo.Adapter.ListAdapter;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.JobAttributes;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by compware on 5/15/2018.
 */

public class ListFragment extends Fragment
{
    ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.list_view, container, false);

        // Set the adapter
        mListView = view.findViewById(R.id.list_job);

        // Set OnItemClickListener so we can be notified on item clicks
        //mListView.setOnItemClickListener(this);
        calltry();

        return view;
    }

    private void calltry() {

        try {
            ApiInterface service = ApiClient.getRetrofit().create(ApiInterface.class);
            Call<List<JobAttributes>> call = service.getJobInfo();

            call.enqueue(new Callback<List<JobAttributes>>() {
                @Override
                public void onResponse(Call<List<JobAttributes>> call, Response<List<JobAttributes>> response) {
                    //Log.d("onResponse", response.message());

                    List<JobAttributes> userList = response.body();
                    //layoutManager = new LinearLayoutManager(MainActivity.this);
                    mListView.setAdapter(new ListAdapter(getActivity(),userList));


                }

                @Override
                public void onFailure(Call<List<JobAttributes>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
        }
    }
}
