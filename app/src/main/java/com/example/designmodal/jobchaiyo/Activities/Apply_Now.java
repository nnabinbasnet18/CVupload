package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Apply_Now extends AppCompatActivity {
    EditText email,name,phone;
    Button uploadCv,submit;
    String file_path;
    String content_type;
    File f;
    ProgressDialog progress;
    String AppliedName ;
    String AppliedEmail ;
    String AppliedPhone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply__now);
        email =(EditText)findViewById(R.id.email_apply);
        name =(EditText)findViewById(R.id.full_name);
        phone = (EditText) findViewById(R.id.contact_no);
        uploadCv = (Button) findViewById(R.id.btn_cv);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                return;
            }
        }
        enable_button();
        enable_button1();


    }

    private void enable_button1() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try {
                    submitcv();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void enable_button()
    {

        uploadCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(Apply_Now.this)
                        .withFilter(Pattern.compile("^.*.(pdf|doc|docx)$"))
                        .withRequestCode(10)
                        .start();

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if(requestCode == 10 && resultCode == RESULT_OK){
                    //String[] mimeTypes ={"application/msword"};
                     f= new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                     content_type = URLConnection.guessContentTypeFromName(f.getName());
                     file_path = f.getAbsolutePath();
                     Toast.makeText(this, "File path"+file_path, Toast.LENGTH_SHORT).show();
        }

    }
    private void submitcv() throws IOException {
        if (file_path==null){
            Toast.makeText(this, "Upload the cv first", Toast.LENGTH_SHORT).show();
        }else {
            AppliedName = name.getText().toString();
            AppliedEmail = email.getText().toString();
            AppliedPhone = phone.getText().toString();

            OkHttpClient client = new OkHttpClient();
            RequestBody file_body = RequestBody.create(MediaType.parse(content_type), f);
            RequestBody request_body = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("type", content_type)
                    .addFormDataPart("uploaded_file", file_path.substring(file_path.lastIndexOf("/") + 1), file_body)
                    .addFormDataPart("full_name",AppliedName)
                    .addFormDataPart("email",AppliedEmail)
                    .addFormDataPart("contact",AppliedPhone)
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.1.88/job_chaheyo/post_cv.php")
                    .post(request_body)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(this, "Application Submitted!! Will email you soon..", Toast.LENGTH_SHORT).show();
                file_path = null;
                name.setText(null);
                email.setText(null);
                phone.setText(null);
            }

        }

    }


}
