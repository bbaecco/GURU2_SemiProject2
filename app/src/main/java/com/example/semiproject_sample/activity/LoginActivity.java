package com.example.semiproject_sample.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.db.FileDB;

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
            String memId = medtId.getText().toString();
            String memPw = medtPw.getText().toString();

            //fileDB에서 일단 찾고 널이 넘어왔을 경우에는 없는 회원 토스트 / 있을 경우는 패스워드가 일치하는지 보고 패스워드 까지 맞으면 메인 화면으로 넘어간다

            MemberBean memberBean = FileDB.getFindMember(LoginActivity.this, memId);
            if(memberBean == null){
                Toast.makeText(LoginActivity.this, "해당 아이디는 없다", Toast.LENGTH_SHORT).show();
                return;
            }
            //패스워드 비교
            if(TextUtils.equals(memberBean.memPw, memPw)){
                FileDB.setLoginMember(LoginActivity.this, memberBean);  //저장
                //비밀번호 일치
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(LoginActivity.this, "패스워드 일치 ㄴㄴ", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    };

    //회원가입 버튼 클릭 이벤트
    private View.OnClickListener mBtnJoinClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(LoginActivity.this, CameraCapture2Activity.class);
            startActivity(i);
            //여기서 this = mBtnJoinClick > 왜냐면 new로 선언함으로써 mBtnJoinClick이 익명 클래스가 됨 > 그래서 LoginActivity를 this로 쓰고 싶으면 LoginActivity.this로 써야 함
        }
    };
}
