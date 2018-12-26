package com.electricity.hasee.electricity;

import android.app.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electricity.hasee.electricity.XUtil.FlowLayout;
import com.electricity.hasee.electricity.XUtil.XUtil;
import com.electricity.hasee.electricity.adapter.MyRecyclerViewAdapter;
import com.electricity.hasee.electricity.adapter.SearchRecyclerViewAdapter;
import com.electricity.hasee.electricity.entity.HistorySearch;
import com.electricity.hasee.electricity.entity.HotSearch;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.DbManager;
import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static com.electricity.hasee.electricity.XUtil.MyUtils.String_length;

public class SearchActivity extends AppCompatActivity {

    @ViewInject(R.id.back_page)
    private ImageButton back_page;
    @ViewInject(R.id.search_icon)
    private ImageButton search_icon;
    @ViewInject(R.id.search_text)
    private EditText search_text;

    private String search_url = "https://www.wenzhuoge.xyz/electricity/getApp_Search?value=";
    private String hotsearch_url = "https://www.wenzhuoge.xyz/electricity/getApp_HotSearch";

    @ViewInject(R.id.side_slip)
    private FlowLayout flowLayout;
    @ViewInject(R.id.side_slip2)
    private FlowLayout flowLayout2;
    @ViewInject(R.id.history_title)
    private LinearLayout history_title;
    private List<HotSearch> list=null;
    private DbManager db;

    @ViewInject(R.id.search_view)
    private LinearLayout search_view;
    @ViewInject(R.id.no_content)
    private LinearLayout no_content;
    @ViewInject(R.id.search_result)
    private RecyclerView search_result;
    private SearchRecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        x.view().inject(this);
        DbManager.DaoConfig daoConfig=XUtil.getDaoConfig();
        db = x.getDb(daoConfig);
        get_hotSearch();



//设置添加或删除item时的动画，这里使用默认动画
        final LinearLayoutManager layoutManager = new LinearLayoutManager(x.app());
        search_result.setLayoutManager(layoutManager);

        search_result.setItemAnimator(new DefaultItemAnimator());



        back_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String search_value=search_text.getText().toString();
                 if(!search_value.equals("")){
                     try {
                         boolean bol=false;
                         for(int i=0;i<list2.size();i++){
                             if(list2.get(i).getValue().equals(search_value)){
                                 bol=true;

                                 break;
                             }
                         }

                         if(bol==false){
                             HistorySearch hs=new HistorySearch();
                             hs.setValue(search_text.getText().toString());
                             db.save(hs);
                         }




                         getData(search_value);

                         // Toast.makeText(SearchActivity.this, "ss", Toast.LENGTH_SHORT).show();
                     } catch (DbException e) {
                         e.printStackTrace();
                     }


                 }




            }
        });



    }
    private List<Phone> phonelist=null;
    public void getData(String search_value){
        RequestParams params = new RequestParams(search_url+ search_value);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                search_view.setVisibility(GONE);
                //     no_content search_result
                try {
                    Gson gson = new Gson();
                    phonelist = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());


                } catch (Exception e) {
                }
                search_view.setVisibility(GONE);
                no_content.setVisibility(View.GONE );
                search_result.setVisibility(View.GONE );
                if(phonelist==null||phonelist.size()==0){
                    no_content.setVisibility(View.VISIBLE );

                }else{
                    search_result.setVisibility(View.VISIBLE );
                    mAdapter = new SearchRecyclerViewAdapter(SearchActivity.this, phonelist);
                    search_result.setAdapter(mAdapter);
                }
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

            @Override
            public boolean onCache(String result) {
                return true;
            }
        });
    }

    List<HistorySearch> list2=null;
    public void get_hotSearch(){
        try {
         //   db.delete(HistorySearch.class);
            List<HistorySearch> list3 = db.findAll(HistorySearch.class);
            list2=new ArrayList<HistorySearch>();



            if(list3==null||list3.size()==0){
                history_title.setVisibility(GONE);
               // flowLayout2.setMinimumHeight(0);
                flowLayout2.setVisibility(GONE);
            }else {
                if(list3.size()>=5){
                    for(int i=0;i<5;i++){
                        list2.add(list3.get(list3.size()-i-1));
                    }
                }else{
                    for(int i=0;i<list3.size();i++){
                       // Toast.makeText(this, list3.size()+"************8", Toast.LENGTH_SHORT).show();


                        list2.add(list3.get(list3.size()-i-1));
                    }
                }
       // Toast.makeText(this, list2.get(0).getValue()+"************8", Toast.LENGTH_SHORT).show();
                for ( int i = 0; i < list2.size(); i++) {
                    final TextView tv = new TextView(SearchActivity.this);
                    tv.setWidth(15 * String_length(list2.get(i).getValue())+40);
                    tv.setHeight(70);
                    tv.setBackgroundResource(R.drawable.textview_border);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(11);
                    tv.setText(list2.get(i).getValue());
                    flowLayout2.addView(tv);
                 //   final String value=  list2.get(i).getValue();
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getData(tv.getText().toString());
                        }
                    });

                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }




        RequestParams params = new RequestParams(hotsearch_url);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    Gson gson = new Gson();
                    list = gson.fromJson(result, new TypeToken<List<HotSearch>>() {
                    }.getType());
                } catch (Exception e) {
                }
         //       list
              //  Toast.makeText(SearchActivity.this, list.size()+"------------------", Toast.LENGTH_SHORT).show();
                for(int i=0;i<list.size();i++){
                    final TextView tv=new TextView(SearchActivity.this);
                    tv.setWidth(17 * String_length(list.get(i).getValue())+40);
                    tv.setHeight(70);
                    tv.setBackgroundResource(R.drawable.textview_border);
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(11);
                    tv.setText(list.get(i).getValue());
                    flowLayout.addView(tv);
                   // final String value=  list2.get(i).getValue();
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getData(tv.getText().toString());
                        }
                    });
                }


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

            @Override
            public boolean onCache(String result) {
                return true;
            }
        });
    }


}
