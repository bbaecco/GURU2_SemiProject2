package com.example.semiproject_sample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semiproject_sample.R;

//여기서 this = LoginActivity = Context = getBaseContext()
public class LoginActivity extends AppCompatActivity {

    //멤버 변수 선언
    private EditText medtId, medtPw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        medtId = findViewById(R.id.edtId);
        medtPw = findViewById(R.id.edtPw);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnJoin = findViewById(R.id.btnJoin);

        btnLogin.setOnClickListener(mBtnLoginClick);
        btnJoin.setOnClickListener(mBtnJoinClick);
        //여기서 this = LoginActivity = Context = getBaseContext()


    } //end onCreate()

    //로그인 버튼 클릭 이벤트
    private View.OnClickListener mBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    //회원가입 버튼 클릭 이벤트
    private View.OnClickListener mBtnJoinClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(LoginActivity.this, JoinActivity.class);
            startActivity(i);
            //여기서 this = mBtnJoinClick > 왜냐면 new로 선언함으로써 mBtnJoinClick이 익명 클래스가 됨 > 그래서 LoginActivity를 this로 쓰고 싶으면 LoginActivity.this로 써야 함
        }
    };
}
