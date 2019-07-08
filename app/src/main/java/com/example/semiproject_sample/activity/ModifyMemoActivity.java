package com.example.semiproject_sample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.bean.MemoBean;
import com.example.semiproject_sample.db.FileDB;
import com.example.semiproject_sample.fragment.FragmentCamera;
import com.example.semiproject_sample.fragment.FragmentMember;
import com.example.semiproject_sample.fragment.FragmentMemo;
import com.example.semiproject_sample.fragment.FragmentMemoWrite;
import com.example.semiproject_sample.fragment.FragmentModifyCamera;
import com.example.semiproject_sample.fragment.FragmentModifyWrite;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ModifyMemoActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private NewMemoActivity.ViewPagerAdapter mViewPagerAdapter;

    //멤버변수
    private TextView memo, date;
    private Button btnModify, btnDelete, btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_memo);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        memo = findViewById(R.id.txtMemo);
        date = findViewById(R.id.txtMemDate);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnSave).setOnClickListener(mBtnClick);

        //탭생성
        mTabLayout.addTab(mTabLayout.newTab().setText("글쓰기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사진찍기"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPager 생성
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        //tab 이랑 viewpager 랑 연결
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    private View.OnClickListener mBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //어떤 버튼이 클릭 됐는지 구분한다
            switch (view.getId()) {
                case R.id.btnCancel:
                    //취소 버튼 클릭시 처리
                    finish();
                    break;

                case R.id.btnModify:
                    //수정 버튼 클릭시 처리
                    saveProc();
                    break;
            }
        }
    };

    //저장버튼 저장처리
    private void saveProc() {
        //1.첫번째 프래그먼트의 EditText 값을 받아온다.
        FragmentMemoWrite f0 = (FragmentMemoWrite)mViewPagerAdapter.instantiateItem(mViewPager,0);
        //2.두번째 프래그먼트의 mPhotoPath 값을 가져온다.
        FragmentCamera f1 = (FragmentCamera)mViewPagerAdapter.instantiateItem(mViewPager,1);

        EditText edtWriteMemo = f0.getView().findViewById(R.id.edtWriteMemo);
        String memoStr = edtWriteMemo.getText().toString();
        String photoPath = f1.mPhotoPath;

        Log.e("SEMI", "memoStr: " + memoStr + ", photoPath: " + photoPath);
        Toast.makeText(this, "memoStr: " + memoStr + ", photoPath: " + photoPath, Toast.LENGTH_LONG).show();

        //TODO 파일DB에 저장처리
        MemoBean memoBean = new MemoBean();
        memoBean.memoPicPath = photoPath;
        memoBean.memo = memoStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        memoBean.memoDate = sdf.format(new Date());


        //메모가 공백인지 체크한다.
        if( TextUtils.isEmpty(memoStr) ){
            Toast.makeText(this, "메모를 입력하세요", Toast.LENGTH_LONG).show();
            return;
        }

        //사진이 공백인지 체크한다.
        if(photoPath == null){
            Toast.makeText(this, "사진을 찍으세요", Toast.LENGTH_LONG).show();
            return;
        }

        //memoBean을 파일로 저장한다 => JSON 변환 후
        MemberBean memberBean = FileDB.getLoginMember(this);
        FileDB.addMemo(ModifyMemoActivity.this, memberBean.memId, memoBean);

        //메모 작성 완료
        Toast.makeText(this, "메모가 작성되었습니다.", Toast.LENGTH_LONG).show();
        finish();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private int tabCount;

        public ViewPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.tabCount = count;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new FragmentMemoWrite();
                case 1:
                    return new FragmentCamera();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
