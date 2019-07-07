package com.example.semiproject_sample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.fragment.FragmentMember;
import com.example.semiproject_sample.fragment.FragmentMemo;
import com.example.semiproject_sample.fragment.FragmentModifyCamera;
import com.example.semiproject_sample.fragment.FragmentModifyWrite;
import com.google.android.material.tabs.TabLayout;

public class ModifyMemoActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_memo);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        //탭 생성
        mTabLayout.addTab(mTabLayout.newTab().setText("글쓰기"));
        mTabLayout.addTab(mTabLayout.newTab().setText("사진 찍기"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPager 생성
        MainActivity.ViewPagerAdapter adapter = new MainActivity.ViewPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        //Tab이랑 viewpager랑 연결
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private int tabCount;

        public ViewPagerAdapter(FragmentManager fm, int count){
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
            return tabCount;  //실수하면 안됨! 만들어 놓은걸로 바꿔야 함
        }
    }
}
