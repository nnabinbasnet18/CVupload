package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.DataManager.ApiClient;
import com.example.designmodal.jobchaiyo.DataManager.ApiInterface;
import com.example.designmodal.jobchaiyo.PdfClass;
import com.example.designmodal.jobchaiyo.R;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.net.URLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstActivity extends AppCompatActivity {
    Button button1,button2;
    EditText imagename;
    ImageView imageView;
    private static final int PDF_REQUEST=777;
    private Bitmap bitmap;

    public int PDF_REQ_CODE = 1;

    String PdfNameHolder, PdfPathHolder, PdfID;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);

        imagename=(EditText)findViewById(R.id.editText1);
        //imageView=(ImageViewfindViewById(R.id.imageView1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //selectImage();
                selectPdf();


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // uploadImage();
                PdfUploadFunction();

            }
        });

    }



    private void selectPdf() {
        Intent intent = new Intent();

        intent.setType("application/pdf");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            File file=new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
            String content_type = URLConnection.guessContentTypeFromName(file.getName());
            String file_path = file.getAbsolutePath();
        }
    }

    private void PdfUploadFunction() {
        PdfNameHolder = imagename.getText().toString();

        PdfPathHolder = FilePath.getPath(this, uri);

        RequestBody Title=RequestBody.create(MediaType.parse("text/plain"),imagename.getText().toString());
        RequestBody Pdf=RequestBody.create(MediaType.parse("application/pdf"), PdfPathHolder);
        ApiInterface apiInterface= ApiClient.getRetrofit().create(ApiInterface.class);
        Call<PdfClass> call=apiInterface.PdfUploadFunction(Title,Pdf);

        call.enqueue(new Callback<PdfClass>() {
            @Override
            public void onResponse(Call<PdfClass> call, Response<PdfClass> response) {

                PdfClass pdfClass=response.body();
                Toast.makeText(getApplicationContext(),"Server Response: "+pdfClass.getResponse(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<PdfClass> call, Throwable t) {

            }
        });

    }
    public void AllowRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }
    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
