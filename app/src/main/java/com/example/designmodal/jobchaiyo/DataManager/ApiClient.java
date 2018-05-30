package com.example.designmodal.jobchaiyo.DataManager;

/**
 * Created by compware on 5/15/2018.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by compware on 5/13/2018.
 */

public class ApiClient {

    public static final String BASE_URL = "http://192.168.100.13/job_chaheyo/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient().newBuilder()
                            .connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .build())
                    .build();
        }
        return  retrofit;
    }
}