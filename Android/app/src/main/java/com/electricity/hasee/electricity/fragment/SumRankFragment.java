package com.electricity.hasee.electricity.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.adapter.MyRecyclerViewAdapter;
import com.electricity.hasee.electricity.adapter.RankRecyclerViewAdapter;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
@ContentView(R.layout.sum_ranking)
public class SumRankFragment extends BaseFragment {

    public static SumRankFragment newInstance(String value){
        SumRankFragment sumRankFragment = new SumRankFragment();
        Bundle bundle = new Bundle();
        bundle.putString("value",value);
        sumRankFragment.setArguments(bundle);
        return sumRankFragment;
    }
    public int index=1;
    private String phone_url = "https://www.wenzhuoge.xyz/electricity/getApp_Phone";
    @ViewInject(R.id.recycler_sumrankview)
    private RecyclerView mRecyclerView;
    private List<Phone> list ;
    private String value="";
    private RankRecyclerViewAdapter mAdapter;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();

        if (bundle != null){
            value= bundle.getString("value");

        }else {
            value="getApp_PhoneXiaomi_Ranking";
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
       // LinearLayoutManager layoutManager = new LinearLayoutManager(x.app());


        
        RequestParams params = new RequestParams(phone_url+value+"_Ranking_All?index="+index);
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
                    mAdapter = new RankRecyclerViewAdapter(x.app(), list,1,value,false);
                }else{
                    mAdapter = new RankRecyclerViewAdapter(x.app(), list,0,value,false);
                    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                            super.onScrolled(recyclerView, dx, dy);

                            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                            int totalItemCount = layoutManager.getItemCount();

                            int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                            if ( totalItemCount < (lastVisibleItem + 2)) {
                                if((mAdapter.getItemCount()-1)%10!=0||(mAdapter.getItemCount()-1)<10){
                                    //  Toast.makeText(x.app(), mAdapter.getItemCount()+"888", Toast.LENGTH_SHORT).show();

                                    mAdapter.changeState(2);
                                }else{


                                    getData2();
                                }

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
    }

    List<Phone> list2;

    private void getData2() {
        index++;
        RequestParams params = new RequestParams(phone_url+value+"_Ranking_All?index="+index);
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
                    mAdapter.changeState(2);
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

}
