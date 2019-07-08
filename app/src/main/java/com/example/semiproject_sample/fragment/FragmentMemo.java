package com.example.semiproject_sample.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.activity.NewMemoActivity;
import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.db.FileDB;

import java.io.File;
import java.util.zip.Inflater;

public class FragmentMemo extends Fragment {

    public ListView mLstMemo;  //mainActivity에서 접근할 일이 있어서 public으로 선언

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo, container, false);

        mLstMemo = view.findViewById(R.id.lstMemo);
        view.findViewById(R.id.btnNewMemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //새메모 작성 화면으로 이동
                Intent i = new Intent(getActivity(), NewMemoActivity.class);
                startActivity(i);
            }
        });



        return view;
    }

}
