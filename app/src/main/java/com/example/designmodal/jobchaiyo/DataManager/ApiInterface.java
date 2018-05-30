package com.example.designmodal.jobchaiyo.DataManager;

import com.example.designmodal.jobchaiyo.JobAttributes;
import com.example.designmodal.jobchaiyo.PdfClass;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by compware on 5/15/2018.
 */

public interface ApiInterface {
    @GET("postJobs.php")
    Call<List<JobAttributes>> getJobInfo();

    @Multipart
    @POST("upload.php")
    Call<PdfClass> PdfUploadFunction (@Part("title") RequestBody title, @Part("pdf") RequestBody image);
}
