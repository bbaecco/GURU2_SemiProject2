package com.example.semiproject_sample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.semiproject_sample.R;

public class JoinActivity extends AppCompatActivity {

    //멤버변수
    private ImageView mimgProfile;
    private EditText medtId, medtName, medtPw1, medtPw2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        mimgProfile = findViewById(R.id.imgProfile);
        medtId = findViewById(R.id.edtId);
        medtName = findViewById(R.id.edtName);
        medtPw1 = findViewById(R.id.edtPw1);
        medtPw2 = findViewById(R.id.edtPw2);

        //카메라 버튼
        findViewById(R.id.btnCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureCamera();
            }
        });

        //회원가입 버튼
        findViewById(R.id.btnJoin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                joinPrecess();
            }
        });
    }  //end onCreate()

    //카메라 작업 시작
    private void captureCamera(){

    }

    //회원가입 작업 시작
    private void joinPrecess(){

    }

}  //end Class
