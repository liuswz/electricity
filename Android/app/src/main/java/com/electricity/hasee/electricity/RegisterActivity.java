package com.electricity.hasee.electricity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.electricity.hasee.electricity.XUtil.MyUtils;
import com.electricity.hasee.electricity.fragment.PeopleFragment;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class RegisterActivity extends AppCompatActivity {

    @ViewInject(R.id.register_back)
    private ImageButton register_back;
    @ViewInject(R.id.register_phonenumber)
    private EditText register_phonenumber;
    @ViewInject(R.id.register_password)
    private EditText register_password;
    @ViewInject(R.id.register_password2)
    private EditText register_password2;
    @ViewInject(R.id.register_button)
    private Button register_button;



    @ViewInject(R.id.phone_wrongtext)
    private TextView phone_wrongtext;
    @ViewInject(R.id.pass_wrongtext)
    private TextView pass_wrongtext;
    @ViewInject(R.id.pass_wrongtext2)
    private TextView pass_wrongtext2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        x.view().inject(this);
        final String flag = getIntent().getStringExtra("flag");
        register_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phonenum=register_phonenumber.getText().toString();
                final String password=register_password.getText().toString();
                final String password2=register_password2.getText().toString();
                if(!MyUtils.isMobileNO(phonenum)){
                 /*   register_phonenumber.setFocusable(true);
                    register_phonenumber.setFocusableInTouchMode(true);
                    register_phonenumber.requestFocus();
                    register_phonenumber.requestFocusFromTouch();*/
                    phone_wrongtext.setVisibility(View.VISIBLE);

                }else if(!MyUtils.rexCheckPassword(password)){
            /*        register_password.setFocusable(true);
                    register_password.setFocusableInTouchMode(true);
                    register_password.requestFocus();
                    register_password.requestFocusFromTouch();*/
                    pass_wrongtext.setVisibility(View.VISIBLE);
                }else if(!password2.equals(password)){
            /*        register_password.setFocusable(true);
                    register_password.setFocusableInTouchMode(true);
                    register_password.requestFocus();
                    register_password.requestFocusFromTouch();*/
                    pass_wrongtext2.setVisibility(View.VISIBLE);
                }else{
                    RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_register?username="+phonenum+"&password="+password);
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            if(result.equals("success")){

                                AlertDialog.Builder dialog = new AlertDialog.Builder(RegisterActivity.this);

                                dialog.setTitle("温馨提示");

                                dialog.setMessage("是否直接登入？");

                                dialog.setCancelable(false);

                                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override

                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("username", phonenum);
                                        editor.putString("password", password);
                                        editor.putString("nickname",phonenum);
                                        editor.putString("picture", "");
                                        editor.commit();
                                    /*    FragmentManager manager=getSupportFragmentManager();
                                        FragmentTransaction transaction=manager.beginTransaction();
                                        transaction.replace(R.id.fl_radio_show,new PeopleFragment());
                                        transaction.commit();*/
                                        RegisterActivity.this.finish();
                                        if(flag.equals("1")){

                                            if(LoginActivity.instance!=null){//给一下判空

                                                LoginActivity.instance.finish();//在其它的activity里面使用

                                            }

                                        }

                                    }

                                });

                                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    @Override

                                    public void onClick(DialogInterface dialogInterface, int i) { }

                                });

                                dialog.show();


                            }else{
                             /*   register_phonenumber.setFocusable(true);
                                register_phonenumber.setFocusableInTouchMode(true);
                                register_phonenumber.requestFocus();
                                register_phonenumber.requestFocusFromTouch();*/
                                pass_wrongtext2.setVisibility(View.VISIBLE);
                                pass_wrongtext2.setText("用户名以存在");
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
}
