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
//    private ModifyMemoActivity.ViewPagerAdapter mViewPagerAdapter;
    private ViewPagerAdapter fViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_memo);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        findViewById(R.id.btnCancel).setOnClickListener(mBtnClick);
        findViewById(R.id.btnSave).setOnClickListener(mBtnClick);

        //탭생성
        mTabLayout.addTab(mTabLayout.newTab().setText("글쓰기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사진찍기"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPager 생성
        fViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        //tab 이랑 viewpager 랑 연결
        mViewPager.setAdapter(fViewPagerAdapter);
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
                    //TODO
                    break;
            }
        }
    };

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
                    return new FragmentModifyWrite();
                case 1:
                    return new FragmentModifyCamera();
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }
}
