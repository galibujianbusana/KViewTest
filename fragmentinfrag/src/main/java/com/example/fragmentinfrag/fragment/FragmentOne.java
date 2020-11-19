package com.example.fragmentinfrag.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentinfrag.MainActivity;
import com.example.fragmentinfrag.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class FragmentOne extends Fragment {

    private static final String TAG = "FragmentOne";
    private TabLayout mTb;
    private ViewPager mVp;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_one, container, false);
        //初始化控件
        initView();
        //添加标题
        initTitile();
        //添加fragment
        initFragment();
        //设置适配器
        mVp.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList, mTitleList));
        //将tablayout与fragment关联
        mTb.setupWithViewPager(mVp);
        initTab();

        return  rootView;
    }




    private void initTitile() {
        mTitleList = new ArrayList<>();
        mTitleList.add("第一页");
        mTitleList.add("第二页");
        //设置tablayout模式
        mTb.setTabMode(TabLayout.MODE_FIXED);
        //tablayout获取集合中的名称
        mTb.addTab(mTb.newTab().setText(mTitleList.get(0)));
        mTb.addTab(mTb.newTab().setText(mTitleList.get(1)));
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new FragmentOne());
        mFragmentList.add(new FragmentTwo());
    }

    private void initView() {
        mTb = (TabLayout) rootView.findViewById(R.id.mTb);
        mVp = (ViewPager) rootView.findViewById(R.id.mVp);
    }



    private void initTab() {


        mTb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: - - >   " + tab.getText().toString());
                if(tab.getText().equals("IN 第一页")){
                    mVp.setCurrentItem(0);
                }else if(tab.getText().equals("IN 第二页")){
                    mVp.setCurrentItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        //添加fragment的集合
        private List<Fragment> mFragmentList;
        //添加标题的集合
        private List<String> mTilteLis;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tilteLis) {
            super(fm);
            mFragmentList = fragmentList;
            mTilteLis = tilteLis;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        //获取标题
        @Override
        public CharSequence getPageTitle(int position) {

            return mTilteLis.get(position);
        }
    }


}