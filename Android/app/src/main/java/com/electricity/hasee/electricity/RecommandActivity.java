package com.electricity.hasee.electricity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.electricity.hasee.electricity.adapter.IndividualRandRecyclerViewAdapter;

import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class RecommandActivity extends AppCompatActivity {

    private double gpu_rate;
    private double front_cam_rate;
    private double back_cam_rate;
    private double size_rate;
    private double front_beauty_rate;
    private double back_beauty_rate;
    private double hand_rate;
    private double screan_rate;
    private double battery_rate;
    private double charge_rate;
    private String must_brand;
    private String must_specail;

    public int index=1;
    private String phone_url = "https://www.wenzhuoge.xyz/electricity/getApp_PhoneRecommand?";
    @ViewInject(R.id.recycler_recommandview)
    private RecyclerView mRecyclerView;
    private List<Phone> list ;

    private IndividualRandRecyclerViewAdapter mAdapter;
    @ViewInject(R.id.recommand_back_page)
    private ImageButton recommand_back_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);
        x.view().inject(this);

        recommand_back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommandActivity.this.finish();
            }
        });
        Intent intent = getIntent();//获取传来的intent对象
        gpu_rate = Double.parseDouble(intent.getStringExtra("gpu_rate"));//获取键值对的键名
        front_cam_rate = Double.parseDouble(intent.getStringExtra("front_cam_rate"));
        back_cam_rate = Double.parseDouble(intent.getStringExtra("back_cam_rate"));
        size_rate = Double.parseDouble(intent.getStringExtra("size_rate"));
        front_beauty_rate = Double.parseDouble(intent.getStringExtra("front_beauty_rate"));
        back_beauty_rate = Double.parseDouble(intent.getStringExtra("back_beauty_rate"));
        hand_rate = Double.parseDouble(intent.getStringExtra("hand_rate"));
        screan_rate = Double.parseDouble(intent.getStringExtra("screan_rate"));
        battery_rate = Double.parseDouble(intent.getStringExtra("battery_rate"));
        charge_rate = Double.parseDouble(intent.getStringExtra("charge_rate"));
        must_brand=intent.getStringExtra("must_brand");
        must_specail=intent.getStringExtra("must_specail");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        String recomandurl=phone_url+"gpu_rate="+gpu_rate+"&front_cam_rate="+front_cam_rate+"&back_cam_rate="
        +back_cam_rate+"&size_rate="+size_rate+"&front_beauty_rate="+front_beauty_rate+"&back_beauty_rate="+back_beauty_rate
        +"&hand_rate="+hand_rate+"&screan_rate="+screan_rate+"&battery_rate="+battery_rate+"&charge_rate="
        +charge_rate+"&must_brand="+must_brand+"&must_specail="+must_specail+"&index="+index;

        System.out.println(recomandurl);
        RequestParams params = new RequestParams(recomandurl);
        final ProgressDialog dialog2 = new ProgressDialog(RecommandActivity.this).show(RecommandActivity.this,"提示", "正在计算最适合您的手机");
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
                dialog2.dismiss();
                if(list.size()<10){
                    mAdapter = new IndividualRandRecyclerViewAdapter(x.app(), list,1,gpu_rate, front_cam_rate, back_cam_rate, size_rate,
                            front_beauty_rate, back_beauty_rate, hand_rate, screan_rate, battery_rate, charge_rate);
                }else{
                    mAdapter = new IndividualRandRecyclerViewAdapter(x.app(), list,0,gpu_rate, front_cam_rate, back_cam_rate, size_rate,
                            front_beauty_rate, back_beauty_rate, hand_rate, screan_rate, battery_rate, charge_rate);
                    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

                        @Override
                        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                            super.onScrolled(recyclerView, dx, dy);

                            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                            int totalItemCount = layoutManager.getItemCount();

                            int lastVisibleItem = layoutManager.findLastVisibleItemPosition();

                            if ( totalItemCount < (lastVisibleItem + 2)) {
                                if((mAdapter.getItemCount()-1)%10!=0||(mAdapter.getItemCount()-1)<10){
                                    mAdapter.changeState(2);
                                    //  Toast.makeText(x.app(), "11", Toast.LENGTH_SHORT).show();
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
                dialog2.dismiss();
            }

            //主动调用取消请求的回调方法

            @Override
            public void onCancelled(CancelledException cex) {
                dialog2.dismiss();
            }

            @Override
            public void onFinished() {
                dialog2.dismiss();
            }

        });
    }

    List<Phone> list2;

    private void getData2() {
        index++;
        RequestParams params = new RequestParams(phone_url+"gpu_rate="+gpu_rate+"&front_cam_rate="+front_cam_rate+"&back_cam_rate="
                +back_cam_rate+"&size_rate="+size_rate+"&front_beauty_rate="+front_beauty_rate+"&back_beauty_rate="+back_beauty_rate
                +"&hand_rate="+hand_rate+"&screan_rate="+screan_rate+"&battery_rate="+battery_rate+"&charge_rate="
                +charge_rate+"&must_brand="+must_brand+"&must_specail="+must_specail+"&index="+index);
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
