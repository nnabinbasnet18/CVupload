package com.example.designmodal.jobchaiyo.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.designmodal.jobchaiyo.Fragments.HomeTopFragment;
import com.example.designmodal.jobchaiyo.R;


public class HomeActivityFragment extends AppCompatActivity {

    HomeTopFragment homeTopFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_fragment);

        homeTopFragment = new HomeTopFragment();

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.topContainer,homeTopFragment);
        transaction.commit();

    }
}