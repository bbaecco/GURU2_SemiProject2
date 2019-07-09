package com.example.semiproject_sample.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.semiproject_sample.R;
import com.example.semiproject_sample.activity.ModifyMemoActivity;
import com.example.semiproject_sample.activity.NewMemoActivity;
import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.bean.MemoBean;
import com.example.semiproject_sample.db.FileDB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class FragmentMemo extends Fragment {

    public ListView mLstMemo;  //mainActivity에서 접근할 일이 있어서 public으로 선언

    List<MemberBean> memberBean = new ArrayList<>();
    ListAdapter adapter;

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

    @Override
    public void onResume() {
        super.onResume();
        MemberBean memberBean = FileDB.getLoginMember(getActivity());
        List<MemoBean> memoList = FileDB.getMemoList(getActivity(), memberBean.memId);

        adapter = new MemoAdapter(getActivity(), memoList);
        mLstMemo.setAdapter(adapter);
    }


    class MemoAdapter extends BaseAdapter {
        private Context mContext;
        private List<MemoBean> mMemoList;

        public MemoAdapter(Context context, List<MemoBean> memoList) {
            mContext = context;
            mMemoList = memoList;
        }

        @Override
        public int getCount() {
            return mMemoList.size();
        }

        @Override
        public Object getItem(int i) {
            return mMemoList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_memo, null);

            final MemoBean memoBean = mMemoList.get(position);

            ImageView imgPic = view.findViewById(R.id.imgPic);
            TextView txtMemo = view.findViewById(R.id.txtMemo);
            TextView txtMemoDate = view.findViewById(R.id.txtMemoDate);
            Button btnModify = view.findViewById(R.id.btnModify);
            Button btnDelete = view.findViewById(R.id.btnDelete);
            Button btnDetail = view.findViewById(R.id.btnDetail);

            imgPic.setImageURI( Uri.fromFile(new File(memoBean.memoPicPath)) );
            txtMemo.setText( memoBean.memo );
            txtMemoDate.setText( memoBean.memoDate );

            //수정 버튼 클릭 이벤트
            btnModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), ModifyMemoActivity.class);
                    startActivity(i);
                }
            });

            //삭제 버튼 클릭 이벤트
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MemberBean memberBean = FileDB.getLoginMember(getActivity());
                    FileDB.delMemo(getActivity(), memberBean.memId, memoBean.memoID);
                }
            });
            //상세보기 버튼 클릭 이벤트
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity(), ModifyMemoActivity.class);
                    startActivity(i);
                }
            });



            return view;
        }
    }

}
