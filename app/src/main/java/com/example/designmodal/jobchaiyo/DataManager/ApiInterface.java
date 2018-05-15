package com.example.designmodal.jobchaiyo.DataManager;

import com.example.designmodal.jobchaiyo.JobAttributes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by compware on 5/15/2018.
 */

public interface ApiInterface {
    @GET("postJobs.php")
    Call<List<JobAttributes>> getJobInfo();
}
