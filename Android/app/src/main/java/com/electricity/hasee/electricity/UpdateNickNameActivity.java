package com.electricity.hasee.electricity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class UpdateNickNameActivity extends AppCompatActivity {

    @ViewInject(R.id.unn_back)
    private ImageButton unn_back;
    @ViewInject(R.id.unn_save)
    private TextView unn_save;
    @ViewInject(R.id.unn_search_text)
    private EditText unn_search_text;

    private String update_url = "https://www.wenzhuoge.xyz/electricity/setApp_nickname?username=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nick_name);
        x.view().inject(this);
        unn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateNickNameActivity.this.finish();
            }
        });
        SharedPreferences sharedPreferences = x.app().getSharedPreferences("user", Context.MODE_PRIVATE);
        final String username = sharedPreferences.getString("username", null);

        unn_search_text.setText(sharedPreferences.getString("nickname", null));
        unn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = unn_search_text.getText().toString();
                if(value.equals("")){
                    Toast.makeText(UpdateNickNameActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    setData(username,value);
                }
            }
        });
    }

    void setData(String username, final String nickname){
        RequestParams params = new RequestParams(update_url+ username+"&nickname="+nickname);
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                //     no_content search_result
              //  Toast.makeText(UpdateNickNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
             //   Toast.makeText(UpdateNickNameActivity.this, result+"888888888888", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("nickname", nickname);
                editor.commit();
                UpdateNickNameActivity.this.finish();

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
