package com.example.designmodal.jobchaiyo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Navin on 5/29/2018.
 */

public class PdfClass {
    //This is a Model Class Retrofit

    @SerializedName("title")
    private String Title;

    @SerializedName("pdf")
    private String Pdf;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
