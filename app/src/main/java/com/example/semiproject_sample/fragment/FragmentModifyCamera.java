package com.example.semiproject_sample.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.semiproject_sample.R;



public class FragmentModifyCamera extends Fragment {

    //사진이 저장된 단말기상의 실제 경로
    public String mPhotoPath = null;
    private Uri mCaptureUri;
    private ImageView mimgCamera;
    public static final int REQUEST_IMAGE_CAPTURE = 200;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_modify_camera, container, false);
    }

}
