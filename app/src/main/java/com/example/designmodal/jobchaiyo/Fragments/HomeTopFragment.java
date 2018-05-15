package com.example.designmodal.jobchaiyo.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.designmodal.jobchaiyo.R;


/**
 * Created by Kuldip on 5/8/2018.
 */

public class HomeTopFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View  view = inflater.inflate(R.layout.search_view,null);
        return view;
    }
}
