package com.electricity.hasee.electricity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.electricity.hasee.electricity.XUtil.ScrollBottomScrollView;
import com.electricity.hasee.electricity.adapter.CommentRecyclerviewAdapter;
import com.electricity.hasee.electricity.adapter.GridViewAdapter;
import com.electricity.hasee.electricity.adapter.MyRecyclerViewAdapter;
import com.electricity.hasee.electricity.entity.Comment;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.xbanner.XBanner;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.electricity.hasee.electricity.XUtil.MyUtils.String_length;

public class DetailActivity extends AppCompatActivity {


    @ViewInject(R.id.banner_detail)
    private XBanner banner;

    @ViewInject(R.id.detail_ai_num)
    private TextView detail_ai_num; //detail_ai_num detail_back_beauty detail_back_beauty_num detail_back_cam_num
    @ViewInject(R.id.detail_back_beauty)
    private TextView detail_back_beauty;
    @ViewInject(R.id.detail_back_beauty_num)
    private TextView detail_back_beauty_num;
    @ViewInject(R.id.detail_back_cam)
    private TextView detail_back_cam;
    @ViewInject(R.id.detail_back_cam_num)
    private TextView detail_back_cam_num;

    @ViewInject(R.id.detail_battery) // detail_battery detail_charge detail_charge_num detail_cheapestShopLink detail_click_num
    private TextView detail_battery;// detail_consume_power detail_consume_power_num detail_name detail_size detail_self_comment
    @ViewInject(R.id.detail_charge)//detail_sum_score detail_type1 detail_price1 detail_officialShopLink detail_uiname detail_ui_num
    private TextView detail_charge;// detail_cpu detail_rechangnum detail_cpu_num detail_gamenum detail_cpu_num2 detail_gpu_num detail_romname
    @ViewInject(R.id.detail_charge_num)//detail_rom_num detail_front_cam detail_front_cam_num detail_front_beauty detail_front_beauty_num detail_hand_num detail_screen
    private TextView detail_charge_num;//detail_screen_num detail_special detail_special_num
    @ViewInject(R.id.detail_cheapestShopLink)
    private TextView detail_cheapestShopLink;
    @ViewInject(R.id.detail_click_num)
    private TextView detail_click_num;

    @ViewInject(R.id.detail_consume_power)
    private TextView detail_consume_power;
    @ViewInject(R.id.detail_consume_power_num)
    private TextView detail_consume_power_num;
    @ViewInject(R.id.detail_name)
    private TextView detail_name;
    @ViewInject(R.id.detail_size)
    private TextView detail_size;
    @ViewInject(R.id.detail_self_comment)
    private TextView detail_self_comment;

    @ViewInject(R.id.detail_sum_score)
    private TextView detail_sum_score;
    @ViewInject(R.id.detail_type1)
    private TextView detail_type1;
    @ViewInject(R.id.detail_price1)
    private TextView detail_price1;
    @ViewInject(R.id.detail_officialShopLink)
    private TextView detail_officialShopLink;
    @ViewInject(R.id.detail_uiname)
    private TextView detail_uiname;
    @ViewInject(R.id.detail_ui_num)
    private TextView detail_ui_num;

    @ViewInject(R.id.detail_cpu)
    private TextView detail_cpu;
    @ViewInject(R.id.detail_rechangnum)
    private TextView detail_rechangnum;

    @ViewInject(R.id.detail_gamenum)
    private TextView detail_gamenum;
    @ViewInject(R.id.detail_cpu_num2)
    private TextView detail_cpu_num2;
    @ViewInject(R.id.detail_gpu_num)
    private TextView detail_gpu_num;
    @ViewInject(R.id.detail_romname)
    private TextView detail_romname;

    @ViewInject(R.id.detail_rom_num)
    private TextView detail_rom_num;
    @ViewInject(R.id.detail_front_cam)
    private TextView detail_front_cam;
    @ViewInject(R.id.detail_front_cam_num)
    private TextView detail_front_cam_num;
    @ViewInject(R.id.detail_front_beauty)
    private TextView detail_front_beauty;
    @ViewInject(R.id.detail_front_beauty_num)
    private TextView detail_front_beauty_num;
    @ViewInject(R.id.detail_hand_num)
    private TextView detail_hand_num;
    @ViewInject(R.id.detail_screen)
    private TextView detail_screen;

    @ViewInject(R.id.detail_screen_num)
    private TextView detail_screen_num;
    @ViewInject(R.id.detail_special)
    private TextView detail_special;
    @ViewInject(R.id.detail_special_num)
    private TextView detail_special_num;
    @ViewInject(R.id.detail_score)
    private TextView detail_score;
    @ViewInject(R.id.detail_different_price)
    private TextView detail_different_price;


    @ViewInject(R.id.type3_layout)
    private LinearLayout type3_layout;
    @ViewInject(R.id.type2_layout)
    private LinearLayout type2_layout;
    @ViewInject(R.id.detail_back_page)
    private ImageButton back_page;


    @ViewInject(R.id.detail_collection)
    private TextView detail_collection;
    private String id;
    private String name;

    @ViewInject(R.id.add_comment)
    private TextView add_comment;
    public int index=1;
    private String comment_url = "https://www.wenzhuoge.xyz/electricity/getApp_phoneComment?productId=";
    private CommentRecyclerviewAdapter mAdapter;
    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.scrollview)
    private ScrollBottomScrollView scrollview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        x.view().inject(this);
        Intent intent = getIntent();//获取传来的intent对象
         id = intent.getStringExtra("id");//获取键值对的键名
        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.finish();
            }
        });
        getData();
        add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
                // String password = sharedPreferences.getString("password", null);
                if(username==null||username.equals("")){
                    Toast.makeText(DetailActivity.this, "请先登入", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);

                }else{
                    Intent intent = new Intent(DetailActivity.this,AddCommentActivity.class);

                    intent.putExtra("id",id);
                    intent.putExtra("name",name);
                    startActivity(intent);
                }

            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    private  Phone p=null;
    private List<Phone> list=null;
    void getData(){

        RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_PhoneDetails?id="+id);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    list = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());
                    if(list.size()!=0&&list!=null){
                        p = list.get(0);
                    }else{
                        p=new Phone();
                    }
                    name=p.getName();
                    initView(p);
                } catch (Exception e) {
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
    List<String> list_path =new ArrayList<>();
    private RequestOptions options;
    void initView(Phone p){
        DecimalFormat df = new DecimalFormat("#.0");
        detail_score.setText(df.format(p.getSum_score()/(p.getPrice1()-p.getDifferent_price()))+"分");
        detail_ai_num.setText(p.getAi_num()+"分");
        detail_back_beauty.setText(p.getBack_beauty());
        detail_back_beauty_num.setText(p.getBack_beauty_num()+"分");
        detail_back_cam_num.setText(p.getBack_cam_num()+"分");
        detail_back_cam.setText(p.getBack_cam());
        detail_battery.setText(p.getBattery()+"毫安");
        detail_charge.setText(p.getCharge());
        detail_charge_num.setText(p.getCharge_num()+"分");
//        detail_cheapestShopLink.setText(p.getAi_num()+"分");
        detail_click_num.setText("点击量："+p.getClick_num());
        detail_consume_power.setText(p.getConsume_power());
        detail_consume_power_num.setText(p.getConsume_power_num()+"分");
        detail_name.setText(p.getName());

        detail_size.setText(p.getSize()+"寸");
        detail_self_comment.setText(p.getSelf_comment());
        detail_sum_score.setText(p.getSum_score()+"分");
        detail_type1.setText(p.getType1());
        detail_price1.setText(p.getPrice1()+"元");
        detail_uiname.setText(p.getUi());
        detail_ui_num.setText(p.getUi_num()+"分");
        detail_cpu.setText(p.getCpu());
        detail_rechangnum.setText(p.getCpu_num()+"分");

        detail_gamenum.setText((p.getCpu_num()+p.getGpu_num())/2+"分");
        detail_cpu_num2.setText(p.getCpu_num()+"分");
        detail_gpu_num.setText(p.getGpu_num()+"分");
        detail_romname.setText(p.getRom());
        detail_rom_num.setText(p.getRom_num()+"分");
        detail_front_cam.setText(p.getFront_cam());
        detail_front_cam_num.setText(p.getFront_cam_num()+"分");
        detail_front_beauty.setText(p.getFront_beauty());
        detail_front_beauty_num.setText(p.getFront_beauty_num()+"分");
        detail_hand_num.setText(p.getHand_num()+"分");

        detail_screen.setText(p.getScreen());
        detail_screen_num.setText(p.getScreen_num()+"分");
        detail_special.setText(p.getSpecial());
        detail_special_num.setText(p.getSpecial_num()+"分");
        detail_different_price.setText(p.getDifferent_price()+"元");
        if(!(p.getType2().equals("")||p.getPrice2()==0||p.getType2()==null)){
            TextView tv = new TextView(DetailActivity.this);
            tv.setPadding(85,0,0,0);

            tv.setWidth(300);
            tv.setHeight(80);


            tv.setTextSize(18);
            tv.setText(p.getType2());
            type2_layout.addView(tv);
            TextView tv2 = new TextView(DetailActivity.this);
            tv2.setWidth(600);
            tv2.setHeight(80);
            tv2.setPadding(200,0,0,0);

            tv2.setTextSize(18);
            tv2.setText(p.getPrice2()+"元");
            type2_layout.addView(tv2);
        }
        if(!(p.getType3().equals("")||p.getPrice3()==0||p.getType3()==null)) {
            TextView tv = new TextView(DetailActivity.this);
            tv.setPadding(85,0,0,0);

            tv.setWidth(300);
            tv.setHeight(80);


            tv.setTextSize(18);
            tv.setText(p.getType3());

            TextView tv2 = new TextView(DetailActivity.this);
            tv2.setWidth(600);
            tv2.setHeight(80);
            tv2.setPadding(200,0,0,0);

            tv2.setTextSize(18);
            tv2.setText(p.getPrice3()+"元");

            type3_layout.addView(tv);
            type3_layout.addView(tv2);
        }
        final Intent intent = new Intent(x.app(),WebViewActivity.class);
        final String cheapestUrl=p.getCheapestShopLink();
        final String officialUrl=p.getOfficialShopLink();
        detail_officialShopLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("url",officialUrl);
                startActivity(intent);
            }
        });
        detail_cheapestShopLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("url",cheapestUrl);
                startActivity(intent);
            }
        });
        options = new RequestOptions()
                .placeholder(R.drawable.timg);
        list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+p.getPicture());
        if(p.getPicture2()!=null||!p.getPicture2().equals("")){
            list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+(p.getPicture2()));
        }
        if(p.getPicture3()!=null||!p.getPicture3().equals("")){
            list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+(p.getPicture3()));
        }
        if(p.getPicture4()!=null||!p.getPicture4().equals("")){
                list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+(p.getPicture4()));
        }
        if(p.getPicture5()!=null||!p.getPicture5().equals("")){
            list_path.add("https://www.wenzhuoge.xyz/electricity/img/"+(p.getPicture5()));
        }

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setData(list_path, null);
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(x.app()).load(list_path.get(position)).apply(options)
                        .into((ImageView) view);

            }
        });

        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                //  Toast.makeText(x.this, "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailActivity.this,BigImageActivity.class);
                intent.putExtra("url",list_path.get(position));
                startActivity(intent);
            }
        });

        detail_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
                // String password = sharedPreferences.getString("password", null);
                if(username==null||username.equals("")){
                    Toast.makeText(DetailActivity.this, "请先登入", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);

                }else{
                    RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/setApp_Collection?username="+username+"&product_id="+id+"&type=phone");
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if(result.equals("success")){
                                Toast.makeText(DetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
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




            }
        });



    }
    List<Comment> clist;
    @Override
    public void onResume() {
        super.onResume();
         index=1;
        RequestParams params = new RequestParams(comment_url+id+"&type=phone&index="+index);

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
                //解析result
                try {
                    Gson gson = new Gson();
                    clist = gson.fromJson(result, new TypeToken<List<Comment>>() {
                    }.getType());
                   // Toast.makeText(DetailActivity.this, clist.size()+"*****8", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }
                if(clist.size()<10){
                    mAdapter = new CommentRecyclerviewAdapter(DetailActivity.this, clist,1);
                }else{
                    mAdapter = new CommentRecyclerviewAdapter(x.app(), clist,0);
                    scrollview.registerOnScrollViewScrollToBottom(new ScrollBottomScrollView.OnScrollBottomListener() {

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

    }
    List<Comment> clist2;
    private void getData2() {
        index++;
        RequestParams params = new RequestParams(comment_url+id+"&type=phone&index="+index);
        //   list2 = new ArrayList<Phone>();

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    clist2 = gson.fromJson(result, new TypeToken<List<Comment>>() {
                    }.getType());

                } catch (Exception e) {
                }
                if(clist2.size()==0){
//                    Log.e("这里是点击每一行item的响应事件",""+index);
                    index--;
                    ;mAdapter.changeState(2);
                }

                else{

                    clist.addAll(clist2);
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
