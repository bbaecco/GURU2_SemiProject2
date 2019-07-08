package com.example.semiproject_sample.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.semiproject_sample.R;

public class FragmentCamera extends Fragment {

    //사진이 저장된 단말기상의 실제 경로
    public String mPhotoPath = "/sdcard/hello/world.jpg";  //dummy data

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }


}
