package com.electricity.hasee.electricity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.electricity.hasee.electricity.adapter.CollectionListViewAdapter;

import com.electricity.hasee.electricity.adapter.SearchRecyclerViewAdapter;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import static android.view.View.GONE;

public class CollectionActivity extends AppCompatActivity {
    @ViewInject(R.id.collect_listView)
    private ListView collect_listView;
    @ViewInject(R.id.collect_back)
    private ImageButton collect_back;
    private CollectionListViewAdapter mAdapter;
    private String collecturl = "https://www.wenzhuoge.xyz/electricity/getApp_Collection?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        x.view().inject(this);
        collect_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionActivity.this.finish();
            }
        });
      /*  collect_listView.setLayoutManager(new LinearLayoutManager(x.app()));
        collect_listView.setItemAnimator(new DefaultItemAnimator());*/
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
       // String password = sharedPreferences.getString("password", null);
        if(username==null||username.equals("")){
            Toast.makeText(this, "请先登入", Toast.LENGTH_SHORT).show();
            CollectionActivity.this.finish();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }else{
            getData(username);
        }

    }


    private List<Phone> phonelist=null;
    public void getData(final String username){
        RequestParams params = new RequestParams(collecturl+username);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //     no_content search_result
                try {
                    Gson gson = new Gson();
                    phonelist = gson.fromJson(result, new TypeToken<List<Phone>>() {
                    }.getType());


                } catch (Exception e) {
                }

                if(!(phonelist==null||phonelist.size()==0)) {

                    mAdapter = new CollectionListViewAdapter(CollectionActivity.this, phonelist,username);
                    mAdapter.setMode(Attributes.Mode.Single);
                    collect_listView.setAdapter(mAdapter);

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
