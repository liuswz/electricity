package com.electricity.hasee.electricity.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.RankingActivity;
import com.electricity.hasee.electricity.XUtil.ScrollBottomScrollView;
import com.electricity.hasee.electricity.adapter.GridViewAdapter;
import com.electricity.hasee.electricity.adapter.MyRecyclerViewAdapter;
import com.electricity.hasee.electricity.adapter.SearchRecyclerViewAdapter;
import com.electricity.hasee.electricity.entity.Advertisement;
import com.electricity.hasee.electricity.entity.Phone;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.xbanner.XBanner;


import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.tab1)
public class PhoneFragment extends BaseFragment  {

    //    @ViewInject(R.id.iv_pic)
//    private ImageView mIvPic;
//
//    private ImageOptions mImageOptions;
//
//
//    private String mPicPath="http://pic33.nipic.com/20130916/3420027_192919547000_2.jpg";
    public int index=1;
    private String phone_url = "https://www.wenzhuoge.xyz/electricity/getApp_Phone?index=";
    private String phoneAd_url ="https://www.wenzhuoge.xyz/electricity/getApp_Ad?type=1";
    private List<Advertisement> ad_list;
    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private List<Phone> list ;
    private MyRecyclerViewAdapter mAdapter;
/*    @ViewInject(R.id.xiaomi_gridview)
    private GridView xiaomi_gridview;
    @ViewInject(R.id.honor_gridview)
    private GridView honor_gridview;
    @ViewInject(R.id.huawei_gridview)
    private GridView huawei_gridview;
    @ViewInject(R.id.iphone_gridview)
    private GridView iphone_gridview;
    @ViewInject(R.id.oppo_gridview)
    private GridView oppo_gridview;
    @ViewInject(R.id.vivo_gridview)
    private GridView vivo_gridview;
    @ViewInject(R.id.meizu_gridview)
    private GridView meizu_gridview;*/

    @ViewInject(R.id.xiaomi_recyclerview)
    private RecyclerView xiaomi_recyclerview;
    @ViewInject(R.id.honor_recyclerview)
    private RecyclerView honor_recyclerview;
    @ViewInject(R.id.huawei_recyclerview)
    private RecyclerView huawei_recyclerview;
    @ViewInject(R.id.iphone_recyclerview)
    private RecyclerView iphone_recyclerview;
    @ViewInject(R.id.oppo_recyclerview)
    private RecyclerView oppo_recyclerview;
    @ViewInject(R.id.vivo_recyclerview)
    private RecyclerView vivo_recyclerview;
    @ViewInject(R.id.meizu_recyclerview)
    private RecyclerView meizu_recyclerview;

    @ViewInject(R.id.zonghe_rank)
    private LinearLayout zonghe_rank;
    @ViewInject(R.id.richang_rank)
    private LinearLayout richang_rank;
    @ViewInject(R.id.xingneng_rank)
    private LinearLayout xingneng_rank;
    @ViewInject(R.id.photo_rank)
    private LinearLayout photo_rank;
    @ViewInject(R.id.xuhang_rank)
    private LinearLayout xuhang_rank;

    @ViewInject(R.id.xiaomi_more)
    private TextView xiaomi_more;
    @ViewInject(R.id.honor_more)
    private TextView honor_more;
    @ViewInject(R.id.huawei_more)
    private TextView huawei_more;
    @ViewInject(R.id.iphone_more)
    private TextView iphone_more;
    @ViewInject(R.id.oppo_more)
    private TextView oppo_more;
    @ViewInject(R.id.vivo_more)
    private TextView vivo_more;
    @ViewInject(R.id.meizu_more)
    private TextView meizu_more;
   /* @ViewInject(R.id.gank_swipe_refresh_layout)
    private SwipeRefreshLayout swipeRefreshLayout;*/
   /* private Handler handler = new Handler();
    boolean isLoading;*/

    @ViewInject(R.id.banner)
    private XBanner banner;
    private ArrayList<String> list_path;
    private ArrayList<String> list_title;
    @ViewInject(R.id.scrollview)
    private ScrollBottomScrollView sv;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Show_Ad();
        getXiaoMi_data();
        getHonor_data();
        getHuawei_data();
        getIphone_data();
        getOppo_data();
        getVivo_data();
        getMeizu_data();

        initListener();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RequestParams params = new RequestParams(phone_url+index);
        
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
                //解析result
                try {
                    Gson gson = new Gson();
                    list = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                
                if(list.size()<10){
                    mAdapter = new MyRecyclerViewAdapter(x.app(), list,1);
                }else{
                    mAdapter = new MyRecyclerViewAdapter(x.app(), list,0);
                    sv.registerOnScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {

                        @Override
                        public void srollToBottom() {
                            //  Toast.makeText(x.app(), list.size()+"888", Toast.LENGTH_SHORT).show();
                            if((mAdapter.getItemCount()-1)%10!=0||(mAdapter.getItemCount()-1)<10){
                                //  Toast.makeText(x.app(), mAdapter.getItemCount()+"888", Toast.LENGTH_SHORT).show();
                                //   Toast.makeText(x.app(), "1", Toast.LENGTH_SHORT).show();
                                mAdapter.changeState(2);
                            }else{

                                getData2();
                            }
                        }
                    });
                }


//设置添加或删除item时的动画，这里使用默认动画


                mRecyclerView.setAdapter(mAdapter);




            }

            //请求异常后的回调方法

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                
            }

            //主动调用取消请求的回调方法

            @Override
            public void onCancelled(CancelledException cex) {
                
            }

            @Override
            public void onFinished() {
                
            }

        });
//初始化适配器


    /*    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // list.clear();
                        index=1;
                        getData();
                    }
                }, 2000);
                //  list.clear();
                // getData();
            }
        });*/


    }

    @Override

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    List<Phone> list2;

    private void getData2() {
        index++;
        RequestParams params = new RequestParams(phone_url+index);
     //   list2 = new ArrayList<Phone>();
        
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    list2 = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());

                } catch (Exception e) {
                }
                if(list2.size()==0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                    index--;
                   ;mAdapter.changeState(2);
                }

                else{

                    list.addAll(list2);
                    mAdapter.changeState(1);
                }
                
                //     Toast.makeText(x.app(), list.size()+"-----------------", Toast.LENGTH_SHORT).show();

           //     swipeRefreshLayout.setRefreshing(false);
                //  mAdapter.notifyItemRemoved(mAdapter.getItemCount());
                //

            }

            //请求异常后的回调方法

            @Override

            public void onError(Throwable ex, boolean isOnCallback) {
                
            }

            //主动调用取消请求的回调方法

            @Override

            public void onCancelled(CancelledException cex) {
                
            }

            @Override

            public void onFinished() {
                
            }

        });


    }
    List<Phone> xiaomiData;
    void getXiaoMi_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneXiaomi_4Ranking");
        
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    xiaomiData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(xiaomiData.size()!=0){
              //      Toast.makeText(x.app(), xiaomiData.size()+"**************", Toast.LENGTH_SHORT).show();
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                  /*  GridViewAdapter gAdapter=new GridViewAdapter(x.app(),xiaomiData);
                    xiaomi_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter gAdapter = new SearchRecyclerViewAdapter(x.app(), xiaomiData);

                    xiaomi_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    xiaomi_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    xiaomi_recyclerview.setAdapter(gAdapter);

                }
                
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                
            }
            @Override
            public void onFinished() {
                
            }

        });
    }
    List<Phone> honorData;
    void getHonor_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneHonor_4Ranking");
        
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    honorData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(honorData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                  /*  GridViewAdapter  gAdapter=new GridViewAdapter(x.app(),honorData);
                    honor_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), honorData);
                    honor_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    honor_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    honor_recyclerview.setAdapter(mAdapter);
                }
                
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                
            }
            @Override
            public void onFinished() {
                
            }
        });
    }
    List<Phone> huaweiData;
    void getHuawei_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneHuawei_2Ranking");
        
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    huaweiData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(huaweiData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                   /* GridViewAdapter  gAdapter=new GridViewAdapter(x.app(),huaweiData);
                    huawei_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), huaweiData);
                    huawei_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    huawei_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    huawei_recyclerview.setAdapter(mAdapter);
                }
                
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {
                
            }
            @Override
            public void onFinished() {
                
            }
        });
    }
    List<Phone> iphoneData;
    void getIphone_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneIPhone_2Ranking");
        
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    iphoneData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(iphoneData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                   /* GridViewAdapter   gAdapter=new GridViewAdapter(x.app(),iphoneData);
                    iphone_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), iphoneData);
                    iphone_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    iphone_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    iphone_recyclerview.setAdapter(mAdapter);
                }
                
            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                
            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {
                
            }
        });
    }
    List<Phone> oppoData;
    void getOppo_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneOppo_2Ranking");
       
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    oppoData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(oppoData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                  /*  GridViewAdapter gAdapter=new GridViewAdapter(x.app(),oppoData);
                    oppo_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), oppoData);
                    oppo_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    oppo_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    oppo_recyclerview.setAdapter(mAdapter);
                }

            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }
    List<Phone> vivoData;
    void getVivo_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneVivo_2Ranking");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    vivoData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(vivoData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                 /*   GridViewAdapter gAdapter=new GridViewAdapter(x.app(),vivoData);
                    vivo_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), vivoData);
                    vivo_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    vivo_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    vivo_recyclerview.setAdapter(mAdapter);
                }

            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }
    List<Phone> meizuData;
    void getMeizu_data(){
        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneMeizu_2Ranking");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    meizuData = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(meizuData.size()!=0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                 /*   GridViewAdapter gAdapter=new GridViewAdapter(x.app(),meizuData);
                    meizu_gridview.setAdapter(gAdapter);*/
                    SearchRecyclerViewAdapter mAdapter = new SearchRecyclerViewAdapter(x.app(), meizuData);
                    meizu_recyclerview.setLayoutManager(new LinearLayoutManager(x.app()));
                    meizu_recyclerview.setItemAnimator(new DefaultItemAnimator());
                    meizu_recyclerview.setAdapter(mAdapter);
                }

            }
            //请求异常后的回调方法
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            //主动调用取消请求的回调方法
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }


    private RequestOptions options;
    void Show_Ad() {

        options = new RequestOptions()
                .placeholder(R.drawable.timg);
        list_path = new ArrayList<>();
        //放标题的集合
       list_title = new ArrayList<>();
        RequestParams params = new RequestParams(phoneAd_url);
        

        x.http().get(params, new Callback.CommonCallback<String>() {


                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        ad_list = gson.fromJson(result, new TypeToken<List<Advertisement>>() {
                        }.getType());

                        for(int i=0;i<ad_list.size();i++){
                            list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+ad_list.get(i).getPhoto());
                            list_title.add(ad_list.get(i).getDetail());
                            //    list_path.add("https://zhdamache.com/Damache/img/2018-07-25-16-47-16_1.png");
                        }

                      
                        //设置内置样式，共有六种可以点入方法内逐一体验使用。
                        banner.setData(list_path, list_title);
                        // XBanner适配数据
                        banner.setmAdapter(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, View view, int position) {
                                Glide.with(x.app()).load(list_path.get(position)).apply(options)
                                .into((ImageView) view);
                               // x.image().bind((ImageView) view,list_path.get(position),options);

                            }
                        });
                        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                            @Override
                            public void onItemClick(XBanner banner, int position) {
                              //  Toast.makeText(x.this, "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(x.app(),DetailActivity.class);
                                intent.putExtra("id",ad_list.get(position).getPageId()+"");
                                startActivity(intent);
                            }
                        });
                        

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }

        });

    }



    public void initListener() {
        final Intent intent = new Intent(x.app(),RankingActivity.class);
        zonghe_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","1");
                startActivity(intent);
            }
        });
        richang_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","2");
                startActivity(intent);
            }
        });
        xingneng_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","3");
                startActivity(intent);
            }
        });//zonghe_rank richang_rank xingneng_rank photo_rank xuhang_rank xiaomi_more honor_more huawei_more iphone_more oppo_more vivo_more meizu_more

        photo_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","4");
                startActivity(intent);
            }
        });
        xuhang_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","5");
                startActivity(intent);
            }
        });
        xiaomi_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","6");
                startActivity(intent);
            }
        });
        honor_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","7");
                startActivity(intent);
            }
        });
        huawei_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","8");
                startActivity(intent);
            }
        });
        iphone_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","9");
                startActivity(intent);
            }
        });
        oppo_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","10");
                startActivity(intent);
            }
        });
        vivo_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","11");
                startActivity(intent);
            }
        });
        meizu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value","12");
                startActivity(intent);
            }
        });
       /* zonghe_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("value",1);
                startActivity(intent);
            }
        });

        //
        switch (v.getId()){
            case R.id.zonghe_rank:
                intent.putExtra("value",1);
                break;
            case R.id.richang_rank:
                intent.putExtra("value",2);
                break;
            case R.id.xingneng_rank:
                intent.putExtra("value",3);
                break;
            case R.id.photo_rank:
                intent.putExtra("value",4);
                break;
            case R.id.xuhang_rank:
                intent.putExtra("value",5);
                break;
            case R.id.xiaomi_more:
                intent.putExtra("value",6);
                break;
            case R.id.honor_more:
                intent.putExtra("value",7);
                break;
            case R.id.huawei_more:
                intent.putExtra("value",8);
                break;
            case R.id.iphone_more:
                intent.putExtra("value",9);
                break;
            case R.id.oppo_more:
                intent.putExtra("value",10);
                break;
            case R.id.vivo_more:
                intent.putExtra("value",11);
                break;
            case R.id.meizu_more:
                intent.putExtra("value",12);
                break;
        }
        startActivity(intent);*/
    }
}


