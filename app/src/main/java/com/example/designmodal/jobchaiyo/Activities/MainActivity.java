package com.example.designmodal.jobchaiyo.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Importing UploadService Package.
import com.example.designmodal.jobchaiyo.R;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.io.File;
import java.util.StringTokenizer;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    // Creating Buttons.
    Button SelectButton, UploadButton;

    // Creating EditText.
    EditText PdfNameEditText ;

    // Creating URI .
    Uri uri;

    // Server URL.
    public static final String PDF_UPLOAD_HTTP_URL = "http://192.168.1.88/job_chaheyo/post_cv.php";

    // Pdf upload request code.
    public int PDF_REQ_CODE = 1;

    // Define strings to hold given pdf name, path and ID.
    String PdfNameHolder, PdfPathHolder, PdfID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Method to enable runtime permission.
        RequestRunTimePermission();

        // Assign ID'S to button and EditText.
        SelectButton = (Button) findViewById(R.id.Button_Select_PDF_ID);
        UploadButton = (Button) findViewById(R.id.Button_Upload_PDF_ID);
        PdfNameEditText = (EditText) findViewById(R.id.PDF_Name_EditText_ID);

        // Adding click listener to Button.
        SelectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(
                            Intent.createChooser(intent, "Select a File to Upload"),
                            1);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "Please install a File Manager.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Adding click listener to Upload PDF button.
        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to upload PDF on server.
                PdfUploadFunction();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedFileURI = data.getData();
                File file = new File(selectedFileURI.getPath().toString());
                Log.d("", "File : " + file.getName());
//                uploadedFileName = file.getName().toString();
//                tokens = new StringTokenizer(uploadedFileName, ":");
//                first = tokens.nextToken();
//                file_1 = tokens.nextToken().trim();
//                txt_file_name_1.setText(file_1);
            }
        }
    }

    // PDF upload function starts from here.
    public void PdfUploadFunction() {

        // Getting pdf name from EditText.
        PdfNameHolder = PdfNameEditText.getText().toString().trim();

        // Getting file path using Filepath class.
        PdfPathHolder = FilePath.getPath(this, uri);

        // If file path object is null then showing toast message to move file into internal storage.
        if (PdfPathHolder == null) {

            Toast.makeText(this, "Please move your PDF file to internal storage & try again.", Toast.LENGTH_LONG).show();

        }
        // If file path is not null then PDF uploading file process will starts.
        else {

            try {

                PdfID = UUID.randomUUID().toString();

                new MultipartUploadRequest(this, PdfID, PDF_UPLOAD_HTTP_URL)
                        .addFileToUpload(PdfPathHolder, "pdf")
                        .addParameter("name", PdfNameHolder)
                        .setNotificationConfig(new UploadNotificationConfig())
                        .setMaxRetries(5)
                        .startUpload();

            } catch (Exception exception) {

                Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    // Requesting run time permission method starts from here.
    public void RequestRunTimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            Toast.makeText(MainActivity.this,"READ_EXTERNAL_STORAGE permission Access Dialog", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] Result) {

        switch (RC) {

            case 1:

                if (Result.length > 0 && Result[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(MainActivity.this,"Permission Granted", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(MainActivity.this,"Permission Canceled", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


}