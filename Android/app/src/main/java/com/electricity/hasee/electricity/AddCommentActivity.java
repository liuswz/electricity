package com.electricity.hasee.electricity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class AddCommentActivity extends AppCompatActivity {

    private String id;

    private String name;

    @ViewInject(R.id.ac_back)
    private ImageButton ac_back;
    @ViewInject(R.id.ac_save)
    private TextView ac_save;
    @ViewInject(R.id.ac_search_text)
    private EditText ac_search_text;

    private String add_url = "https://www.wenzhuoge.xyz/electricity/setApp_addComment?username=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        x.view().inject(this);
        final Intent intent = getIntent();//获取传来的intent对象
        id = intent.getStringExtra("id");//获取键值对的键名
        name = intent.getStringExtra("name");//获取键值对的键名
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)

        ac_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCommentActivity.this.finish();
            }
        });



        ac_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = ac_search_text.getText().toString();
                if(value.equals("")){
                    Toast.makeText(AddCommentActivity.this, "评价不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    setData( username, id, "phone", value,name);
                }
            }
        });
    }



    void setData(String username,String productId,String type,String content,String productname){
        RequestParams params = new RequestParams(add_url+ username+"&productId="+productId+"&type="+type+"&content="+content+"&productname="+productname);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //     no_content search_result
                //  Toast.makeText(UpdateNickNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
               // Toast.makeText(AddCommentActivity.this, result+"888888888888", Toast.LENGTH_SHORT).show();


                if(result.equals("success")){
                    Toast.makeText(AddCommentActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                    AddCommentActivity.this.finish();
                }else{
                    Toast.makeText(AddCommentActivity.this, "发布失败", Toast.LENGTH_SHORT).show();
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
