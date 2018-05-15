package com.example.designmodal.jobchaiyo.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.Adapter.RecyclerViewAdapter;
import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.JobAttributes;
import com.example.designmodal.jobchaiyo.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class RecycleView_job extends android.app.Fragment {
    RecyclerView recyclerView;

    public RecycleView_job() {

    }

    private LinearLayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        final View view=inflater.inflate(R.layout.activity_recycle_view_job, container, false);
        Toast.makeText(getActivity(), "this is fragment", Toast.LENGTH_SHORT).show();
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        calltry();

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
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(userList);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }

                @Override
                public void onFailure(Call<List<JobAttributes>> call, Throwable t) {

                }
            });
        } catch (Exception e) {
        }
    }


}