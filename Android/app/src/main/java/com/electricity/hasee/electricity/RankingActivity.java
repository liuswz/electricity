package com.electricity.hasee.electricity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.electricity.hasee.electricity.adapter.TabFragmentPagerAdapter;
import com.electricity.hasee.electricity.fragment.LaptopFragment;
import com.electricity.hasee.electricity.fragment.PhoneFragment;
import com.electricity.hasee.electricity.fragment.RankFragment;
import com.electricity.hasee.electricity.fragment.SumRankFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    @ViewInject(R.id.rank_navigation)
    private TabLayout rank_navigation;
    @ViewInject(R.id.recycler_rankview)
    private RecyclerView recycler_rankview;
    @ViewInject(R.id.id_rankviewpager)
    private ViewPager rViewPager;
    @ViewInject(R.id.rank_title)
    private TextView rank_title;
    @ViewInject(R.id.back_page)
    private ImageButton back_page;
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private TabFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        x.view().inject(this);
        Intent intent = getIntent();//获取传来的intent对象
         int data = Integer.parseInt(intent.getStringExtra("value"));//获取键值对的键名

        initView(data);
        rViewPager.setAdapter(mAdapter);

        //设置Viewpager和Tablayout进行联动

        rank_navigation.setupWithViewPager(rViewPager);
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RankingActivity.this.finish();
            }
        });
    //    rank_navigation.setTabMode(TabLayout.MODE_SCROLLABLE);

   //     rank_navigation.setVerticalScrollbarPosition(0);
    }


    private void initData(String rank_url) {

        //初始化导航标题,如果是title在json数据中,在初始化的时候可以使用异步任务加载的形式添加

        mTitles=new ArrayList<>();

        mTitles.add("性价比排行");

        mTitles.add("总体排行");



        //初始化Fragment

        mFragments=new ArrayList<>();

        for (int i = 0; i <mTitles.size() ; i++) {

            if(i==0){

                mFragments.add(new RankFragment().newInstance(rank_url));

            }else if(i==1){

                mFragments.add(new SumRankFragment().newInstance(rank_url));

            }

        }

        //getSupportFragmentManager()是Activity嵌套fragment时使用

        //getChildFragmentManager()是Fragment嵌套Fragment时使用

        mAdapter=new TabFragmentPagerAdapter(getSupportFragmentManager(),mFragments,mTitles);

        mAdapter.notifyDataSetChanged();

    }
    private void initView(int data) {
        switch (data){
            case 1:
                rank_title.setText("综合");
                initData("Total");
                break;
            case 2:
                rank_title.setText("日常");
                initData("Common");
                break;
            case 3:
                rank_title.setText("性能");
                initData("Game");
                break;
            case 4:
                rank_title.setText("拍照");
                initData("Camera");
                break;
            case 5:
                rank_title.setText("续航");
                initData("XuHang");
                break;
            case 6:
                rank_title.setText("小米");
                initData("Xiaomi");
                break;
            case 7:
                rank_title.setText("荣耀");
                initData("Honor");
                break;
            case 8:
                rank_title.setText("华为");
                initData("Huawei");
                break;
            case 9:
                rank_title.setText("苹果");
                initData("IPhone");
                break;
            case 10:
                rank_title.setText("Oppo");
                initData("Oppo");
                break;
            case 11:
                rank_title.setText("Vivo");
                initData("Vivo");
                break;
            case 12:
                rank_title.setText("魅族");
                initData("Meizu");
                break;


        }


    }


}
