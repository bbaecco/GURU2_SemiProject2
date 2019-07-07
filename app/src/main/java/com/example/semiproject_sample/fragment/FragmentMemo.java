package com.example.semiproject_sample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.activity.NewMemoActivity;

import java.util.zip.Inflater;

public class FragmentMemo extends Fragment {

    Button btnNewMemo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_memo, container, false);

        btnNewMemo = view.findViewById(R.id.btnNewMemo);

        //새메모 작성 버튼 클릭 이벤트
        btnNewMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), NewMemoActivity.class);
                startActivity(i);
            }
        });


        return view;
    }

}
