package com.example.semiproject_sample.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.activity.LoginActivity;
import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.db.FileDB;

import java.io.File;

public class FragmentMember extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member, container, false);

        ImageView imgProfile = view.findViewById(R.id.imgProfile);
        TextView txtMemId = view.findViewById(R.id.txtMemId);
        TextView txtMemName = view.findViewById(R.id.txtMemName);
        TextView txtMemPw = view.findViewById(R.id.txtMemPw);
        TextView txtMemDate = view.findViewById(R.id.txtMemDate);

        //파일 DB에서 가져온다
        MemberBean memberBean = FileDB.getLoginMember(getActivity());

        imgProfile.setImageURI( Uri.fromFile(new File(memberBean.photoPath)) );
        txtMemId.setText(memberBean.memId);
        txtMemName.setText(memberBean.memName);
        txtMemPw.setText(memberBean.memPw);
        txtMemDate.setText(memberBean.memRegDate);

        //로그아웃 버튼 클릭 이벤트
        Button btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

}
