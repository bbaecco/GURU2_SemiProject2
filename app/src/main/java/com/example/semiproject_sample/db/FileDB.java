package com.example.semiproject_sample.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.semiproject_sample.bean.MemberBean;
import com.example.semiproject_sample.bean.MemoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class FileDB {

    private static final String FILE_DB = "FileDB";
    private static Gson mGson = new Gson();

    //저장할 때 사용
    private static SharedPreferences getSP(Context context) {
        SharedPreferences sp = context.getSharedPreferences(FILE_DB, Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * 새로운 멤버 추가
     **/
    public static void addMember(Context context, MemberBean memberBean) {
        //1. 기존의 멤버 리스트를 불러온다
        List<MemberBean> memberList = getMemberList(context);

        //2. 기존의 멤버 리스트에 추가한다.
        memberList.add(memberBean);

        //3. 멤버 리스트를 저장한다
        String listStr = mGson.toJson(memberList);

        //4. 저장한다
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", listStr);
        editor.commit();
//        getSP(context).edit().putString("memberList", listStr);

    }

    //기존 멤버 교체 (메모를 수정했을 때 사용)
    public static void setMember(Context context, MemberBean memberBean) {
        //MemberBean findMember = getFindMember(context, memberBean.memId); //해당 멤버 찾기
        //전체 멤버 리스트를 취득한다
        List<MemberBean> memberList = getMemberList(context);
        if (memberList.size() == 0) return;

        for (int i = 0; i < memberList.size(); i++) {  //for each
            MemberBean bean = memberList.get(i);
            if (TextUtils.equals(bean.memId, memberBean.memId)) {
                //같은 멤버ID를 찾았다
                memberList.set(i, memberBean);  //i=교체하고자 하는 인덱스
                break;
            }
        }
        //새롭게 update된 리스트를 저장한다
        String jSonStr = mGson.toJson(memberList);
        //멤버 리스트를 저장한다.
        SharedPreferences.Editor editor = getSP(context).edit();
        editor.putString("memberList", jSonStr);
        editor.commit();

    }

    public static List<MemberBean> getMemberList(Context context) {
        String listStr = getSP(context).getString("memberList", null);
        //저장된 리스트가 없을 경우에 새로운 리스트를 리턴한다.
        if (listStr == null) {
            return new ArrayList<MemberBean>();
        }
        //있을 경우 MemberBean을 Gson 으로 변환한다.
        List<MemberBean> memberList = mGson.fromJson(listStr, new TypeToken<List<MemberBean>>() {
        }.getType());
        return memberList;
    }

    public static MemberBean getFindMember(Context context, String memId) {
        //1. 멤버 리스트를 가져온다
        List<MemberBean> memberList = getMemberList(context);

        //2. for문 돌면서 해당 아이디를 찾는다
        for (MemberBean bean : memberList) {
            if (TextUtils.equals(bean.memId, memId)) { //아이디가 같다
                return bean;
            }
        }

        //3. 찾았을 경우는 해당 MemberBean을 리턴한다.
        //3-2. 못찾았을 경우는 > null리턴
        return null;
    }

    //로그인한 Member Bean 을 저장한다.
    public static void setLoginMember(Context context, MemberBean bean) {
        if (bean != null) {
            String str = mGson.toJson(bean);
            //getSP(context).edit().putString("loginMemberBean", str);
            SharedPreferences.Editor editor = getSP(context).edit();
            editor.putString("loginMemberBean", str);
            editor.commit();
        }
    }

    //로그인한 MemberBean 을 취득한다.
    public static MemberBean getLoginMember(Context context) {
        String str = getSP(context).getString("loginMemberBean", null);
        if (str == null) return null;
        MemberBean memberBean = mGson.fromJson(str, MemberBean.class);
        return memberBean;
    }

    /**
     * 새로운 메모 추가
     **/
    public static void addMemo(Context context, String memId, MemoBean memoBean) {

        MemberBean findMember = getFindMember(context, memId);
        if (findMember == null) return;

        List<MemoBean> memoList = findMember.memoList;
        if (memoList == null) {
            memoList = new ArrayList<>();
        }
        //고유 메모 ID를 생성해준다.
//      memoBean.memoID = memoList.size() + 1;
        memoBean.memoID = System.currentTimeMillis();
        memoList.add(memoBean);
        findMember.memoList = memoList;

        //저장
        setMember(context, findMember);
    }

    //기존 메모 교체
    public static void setMemo(Context context, String memId, MemoBean memoBean) {
        //TODO
        //전체 멤버 리스트를 취득한다
        MemberBean memberBean = getFindMember(context, memId);
       // List<MemoBean> memoList = getMemoList(context, memId);
        if (memberBean.memoList.size() == 0) return;

        for (int i = 0; i < memberBean.memoList.size(); i++) {  //for each
            MemoBean bean = memberBean.memoList.get(i);
            if (bean.memo == memoBean.memo) {
                //같은 멤버ID를 찾았다
                memberBean.memoList.set(i, memoBean);  //i=교체하고자 하는 인덱스
                break;
            }
        }
    }

    //메모 삭제
    public static void delMemo(Context context, String memId, int memoId) {
        //TODO
        MemberBean memberBean = getFindMember(context, memId);
        List<MemoBean> memoList = getMemoList(context, memId);
//        memoList.memoID = memoList.size() - 1;
        memberBean.memoList.remove(memoId - 1);

    }

    public static MemoBean findMemo(Context context, String memId, long memoId) {
        List<MemoBean> memoList = getMemoList(context, memId);

        MemoBean memoBean = null;
        for (MemoBean bean : memoList) {
            if (memoBean.memoID == memoId) {  //아이디가 같다
                memoBean = bean;
            }
        }
        return memoBean;
    }

    //메모 리스트 취득
    public static List<MemoBean> getMemoList(Context context, String memId) {
        MemberBean memberBean = getFindMember(context, memId);
        if (memberBean == null) return null;

        if (memberBean.memoList == null) {
            return new ArrayList<>();
        } else {
            return memberBean.memoList;
        }
    }
}

