package com.electricity.hasee.electricity.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.CollectionActivity;
import com.electricity.hasee.electricity.CommentActivity;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.LoginActivity;
import com.electricity.hasee.electricity.MainActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.RegisterActivity;
import com.electricity.hasee.electricity.SearchActivity;
import com.electricity.hasee.electricity.UserDetailsActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.people)
public class PeopleFragment extends BaseFragment {
    @ViewInject(R.id.search_icon2)
    private ImageButton search_icon2;
    @ViewInject(R.id.touxiang)
    private ImageButton touxiang;
    @ViewInject(R.id.people_dengru)
    private TextView dengru;
    @ViewInject(R.id.people_zhuce)
    private TextView zhuce;
    @ViewInject(R.id.shoucang)
    private LinearLayout shoucang;
    @ViewInject(R.id.zhuxiao)
    private LinearLayout zhuxiao;
    @ViewInject(R.id.commentlayout)
    private LinearLayout commentlayout;
    @ViewInject(R.id.contact_me)
    private LinearLayout contact_me;

    @ViewInject(R.id.login_layout)
    private LinearLayout login_layout;
    @ViewInject(R.id.loginfinish_layout)
    private LinearLayout loginfinish_layout;

    @ViewInject(R.id.nickname_text)
    private TextView nickname_text;
    @ViewInject(R.id.icon_person)
    private ImageView icon_person;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        search_icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),SearchActivity.class);
                startActivity(intent);
            }
        });
        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),LoginActivity.class);
                startActivity(intent);
            }
        });
        dengru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),LoginActivity.class);
                startActivity(intent);
            }
        });
        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),RegisterActivity.class);
                intent.putExtra("flag","1");
                startActivity(intent);
            }
        });
        zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("温馨提示");

                dialog.setMessage("是否退出登入？");

                dialog.setCancelable(false);

                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {

                        SharedPreferences sharedPreferences = x.app().getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", "");
                        editor.putString("password", "");
                        editor.putString("nickname", "");
                        editor.putString("picture", "");
                        editor.commit();
                        login_layout.setVisibility(View.VISIBLE);
                        loginfinish_layout.setVisibility(View.GONE);
                        Toast.makeText(x.app(), "登出成功", Toast.LENGTH_SHORT).show();

                    }

                });

                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {



                    }

                });

                dialog.show();

            }
        });
        commentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),CommentActivity.class);
                startActivity(intent);
            }
        });
        shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),CollectionActivity.class);
                startActivity(intent);
            }
        });
        loginfinish_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),UserDetailsActivity.class);
                startActivity(intent);
            }
        });
        contact_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

                dialog.setTitle("提示");

                dialog.setMessage("您可以添加小编qq号2639147439哦");

                dialog.setCancelable(false);

                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override

                    public void onClick(DialogInterface dialogInterface, int i) {



                    }

                });



                dialog.show();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = x.app().getSharedPreferences("user", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
        String nickname = sharedPreferences.getString("nickname", null);
        String picture = sharedPreferences.getString("picture", null);
        // String password = sharedPreferences.getString("password", null);

        if(username==null||username.equals("")){
            login_layout.setVisibility(View.VISIBLE);
            loginfinish_layout.setVisibility(View.GONE);
        }else{
            login_layout.setVisibility(View.GONE);
            loginfinish_layout.setVisibility(View.VISIBLE);
            nickname_text.setText("昵称："+nickname);
        }
        if(picture==null||picture.equals("")){

        }else{
            RequestOptions options = new RequestOptions().circleCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
            Glide.with(x.app()).load(picture)  .apply(options)
                    .into(icon_person);
        }
    }
}
