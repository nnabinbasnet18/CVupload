package com.example.designmodal.jobchaiyo.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.designmodal.jobchaiyo.R;

public class CompanyRegisterActivity extends AppCompatActivity {
    private EditText et_CName, et_CAddress, et_Website, et_ContactPerson, et_OfficeContact, et_Mobile, et_Email, et_ReEmail, et_OptEmail,
            et_UserName, et_Password, et_RePassword;
    private String CName, CAddress, Website, ContactPerson, OfficeContact, Mobile, Email, ReEmail, OptEmail,tPassword,tRepassword,
            UserName, Password, tEmail,tReEmail,RePassword;
    Button signin, cancel;
    ImageView imageView;
    private static final int PICK_IMAGE=100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_register);
        et_CName = (EditText) findViewById(R.id.CName);
        et_CAddress = (EditText) findViewById(R.id.CAddress);
        et_Website = (EditText) findViewById(R.id.Website);
        et_ContactPerson = (EditText) findViewById(R.id.ContactPerson);
        et_OfficeContact = (EditText) findViewById(R.id.OfficeContact);
        et_Mobile = (EditText) findViewById(R.id.Mobile);
        et_Email = (EditText) findViewById(R.id.Email);
        et_ReEmail = (EditText) findViewById(R.id.ReEmail);
        et_OptEmail = (EditText) findViewById(R.id.OptEmail);
        et_UserName = (EditText) findViewById(R.id.UserName);
        et_Password = (EditText) findViewById(R.id.Password);
        et_RePassword = (EditText) findViewById(R.id.RePassword);
        imageView= (ImageView) findViewById(R.id.image);
        signin = (Button) findViewById(R.id.signin);
        cancel = (Button) findViewById(R.id.cancel);
//          tPassword=et_Password.getText().toString();
//          tRepassword= et_RePassword.getText().toString();
//         tEmail= String.valueOf(et_Email.getText());
//         tReEmail= String.valueOf(et_ReEmail.getText());

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(); //call when button is clicked to validate the input
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();


            }
        });

    }

    private void openGallery() {
        Intent gallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && requestCode==PICK_IMAGE){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
    public void register(){
        intialize();// intializing the input to string variables
        if (!validate()){
            Toast.makeText(this,"Signup has Failed",Toast.LENGTH_SHORT).show();
        }
        else{
            onSignupSuccess();
        }


    }
    public void onSignupSuccess(){
        // to do what will go after the valid input

    }
    public boolean validate(){
        boolean valid=true;
        if(CName.isEmpty()|| CName.length()>32)
        {
            et_CName.setError("Please Enter Valid Company Name");
            valid=false;
        }
        if(CAddress.isEmpty()|| CAddress.length()>32)
        {
            et_CAddress.setError("Please Enter Valid Company Address");
            valid=false;
        }
        if( Website.length()>32)
        {
            et_Website.setError("Please Enter Valid Website");
            valid=false;
        }
        if(ContactPerson.isEmpty())
        {
            et_ContactPerson.setError("Please Enter Valid Contact Person");
            valid=false;
        }
        if(OfficeContact.length()>10)
        {
            et_OfficeContact.setError("Please Enter Valid Office Contact");
            valid=false;
        }
        if(Mobile.isEmpty()|| Mobile.length()>10)
        {
            et_OfficeContact.setError("Please Enter Valid Mobile Number");
            valid=false;
        }
        if(Email.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            et_Email.setError("Please Enter Valid Email Address");
            valid=false;
        }
        if(!ReEmail.equals(Email))
        {
            et_ReEmail.setError("Email not match. Please Reenter");
            valid=false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(OptEmail).matches())
        {
            et_OptEmail.setError("Please Enter Valid Email Address");
            valid=false;
        }
        if(UserName.isEmpty()|| UserName.length()>32)
        {
            et_UserName.setError("Please Enter Valid Username");
            valid=false;
        }
        if(Password.isEmpty()|| Password.length()>32)
        {
            et_Password.setError("Please Enter Valid Password");
            valid=false;
        }
        if(!RePassword.equals(Password))
        {
            et_RePassword.setError("Password not match. Please Reenter your Password");
            valid=false;
        }
        return valid;
    }
    public void intialize()
    {
        CName=et_CName.getText().toString().trim();
        CAddress=et_CAddress.getText().toString().trim();
        Website=et_Website.getText().toString().trim();
        ContactPerson=et_ContactPerson.getText().toString().trim();
        OfficeContact=et_OfficeContact.getText().toString().trim();
        Mobile=et_Mobile.getText().toString().trim();
        Email=et_Email.getText().toString().trim();
        ReEmail=et_ReEmail.getText().toString().trim();
        OptEmail=et_OptEmail.getText().toString().trim();
        UserName=et_UserName.getText().toString().trim();
        Password=et_Password.getText().toString().trim();
        RePassword=et_RePassword.getText().toString().trim();

    }
}