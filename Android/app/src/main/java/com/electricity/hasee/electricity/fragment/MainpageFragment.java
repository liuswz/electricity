package com.electricity.hasee.electricity.fragment;



import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.MainActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.SearchActivity;
import com.electricity.hasee.electricity.adapter.TabFragmentPagerAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.mainpage)
public class MainpageFragment  extends BaseFragment {
    @ViewInject(R.id.search)
    private TextView search;
    @ViewInject(R.id.id_viewpager)
    private ViewPager mViewPager;

    @ViewInject(R.id.tl_homepage_navigation)
    private TabLayout mTabLayout;

    private List<Fragment>mFragments;
    private List<String> mTitles;
    private TabFragmentPagerAdapter mAdapter;


    //适配器

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initData(view);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(x.app(),SearchActivity.class);
                startActivity(intent);

            }
        });
        setData();

    }

    private void setData() {

        mViewPager.setAdapter(mAdapter);

        //设置Viewpager和Tablayout进行联动

        mTabLayout.setupWithViewPager(mViewPager);

//        //将标题设置可以左右摇动而不是移动

//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        //设置预加载页数

//        mViewPager.setOffscreenPageLimit(3);



    }



    private void initData(View view) {

        //初始化导航标题,如果是title在json数据中,在初始化的时候可以使用异步任务加载的形式添加

        mTitles=new ArrayList<>();

        mTitles.add("手机");

        mTitles.add("笔记本");



        //初始化Fragment

        mFragments=new ArrayList<>();

        for (int i = 0; i <mTitles.size() ; i++) {

            if(i==0){

                mFragments.add(new PhoneFragment());

            }else if(i==1){

                mFragments.add(new LaptopFragment());

            }

        }

        //getSupportFragmentManager()是Activity嵌套fragment时使用

        //getChildFragmentManager()是Fragment嵌套Fragment时使用

        mAdapter=new TabFragmentPagerAdapter(getChildFragmentManager(),mFragments,mTitles);

        mAdapter.notifyDataSetChanged();

    }

}
