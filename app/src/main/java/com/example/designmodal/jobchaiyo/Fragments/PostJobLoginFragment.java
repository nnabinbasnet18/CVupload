package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.designmodal.jobchaiyo.Activities.CompanyRegisterActivity;
import com.example.designmodal.jobchaiyo.R;

/**
 * Created by Manjil on 5/10/2018.
 */

public class PostJobLoginFragment extends Fragment {
    private Context context;

    Button register,login;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.postjob_view,null);
        context=container.getContext();
        register=view.findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, CompanyRegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}