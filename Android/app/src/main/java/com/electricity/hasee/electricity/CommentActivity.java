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

import com.electricity.hasee.electricity.adapter.CollectionListViewAdapter;
import com.electricity.hasee.electricity.adapter.CommentRecyclerviewAdapter;
import com.electricity.hasee.electricity.adapter.RankRecyclerViewAdapter;
import com.electricity.hasee.electricity.adapter.UserCommentRecyclerviewAdapter;
import com.electricity.hasee.electricity.entity.Comment;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class CommentActivity extends AppCompatActivity {
    @ViewInject(R.id.collect_recycleView)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.comment_back)
    private ImageButton comment_back;
    private UserCommentRecyclerviewAdapter mAdapter;
    private String collecturl = "https://www.wenzhuoge.xyz/electricity/getApp_userComment?username=";
    private String username;
    public int index=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        x.view().inject(this);

        comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentActivity.this.finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
         username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
        // String password = sharedPreferences.getString("password", null);
        if(username==null||username.equals("")){
            Toast.makeText(this, "请先登入", Toast.LENGTH_SHORT).show();
            CommentActivity.this.finish();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }else{
            mRecyclerView.setLayoutManager(new LinearLayoutManager(CommentActivity.this, LinearLayoutManager.VERTICAL, false));
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            getData();
        }

    }
    List<Comment> list;
    void getData(){
        RequestParams params = new RequestParams(collecturl+username+"&type=phone&index="+index);
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
                //解析result
                try {
                    Gson gson = new Gson();
                    list = gson.fromJson(result, new TypeToken<List<Comment>>() {
                    }.getType());
                } catch (Exception e) {
                }
                if(list.size()<10){
                    mAdapter = new UserCommentRecyclerviewAdapter(CommentActivity.this, list,1);
                }else{
                    mAdapter = new UserCommentRecyclerviewAdapter(x.app(), list,0);
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

    List<Comment> list2;
    private void getData2() {
        index++;
        RequestParams params = new RequestParams(collecturl+username+"&type=phone&index="+index);
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
