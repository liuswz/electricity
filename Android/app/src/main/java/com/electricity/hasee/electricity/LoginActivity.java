package com.electricity.hasee.electricity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.electricity.hasee.electricity.XUtil.MyUtils;
import com.electricity.hasee.electricity.entity.Phone;
import com.electricity.hasee.electricity.entity.User;
import com.electricity.hasee.electricity.fragment.PeopleFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;
import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    @ViewInject(R.id.login_back)
    private ImageButton login_back;
    @ViewInject(R.id.login_phonenumber)
    private EditText login_phonenumber;
    @ViewInject(R.id.login_password)
    private EditText login_password;
    @ViewInject(R.id.login_button)
    private Button login_button;
    @ViewInject(R.id.forget_password)
    private TextView forget_password;
    @ViewInject(R.id.enter_register)
    private TextView enter_register;


    @ViewInject(R.id.phone_wrongtext)
    private TextView phone_wrongtext;
    @ViewInject(R.id.pass_wrongtext)
    private TextView pass_wrongtext;
    private List<User> ulist=null;
    String savepath , imgname;

    public static LoginActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        x.view().inject(this);
        instance=this;

        login_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
        login_phonenumber.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String phonenum=login_phonenumber.getText().toString();
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    phone_wrongtext.setVisibility(View.GONE);
                } else {
                    // 此处为失去焦点时的处理内容
                    if(!MyUtils.isMobileNO(phonenum)){
                       /* login_phonenumber.setFocusable(true);
                        login_phonenumber.setFocusableInTouchMode(true);
                        login_phonenumber.requestFocus();
                        login_phonenumber.requestFocusFromTouch();*/
                        phone_wrongtext.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        login_password.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                final String password=login_password.getText().toString();
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    pass_wrongtext.setVisibility(View.GONE);
                } else {
                    // 此处为失去焦点时的处理内容
                    if(!MyUtils.rexCheckPassword(password)){
                       /* login_password.setFocusable(true);
                        login_password.setFocusableInTouchMode(true);
                        login_password.requestFocus();
                        login_password.requestFocusFromTouch();*/
                        pass_wrongtext.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phonenum=login_phonenumber.getText().toString();
                final String password=login_password.getText().toString();
                if(!MyUtils.isMobileNO(phonenum)){
                   /* login_phonenumber.setFocusable(true);
                    login_phonenumber.setFocusableInTouchMode(true);
                    login_phonenumber.requestFocus();
                    login_phonenumber.requestFocusFromTouch();*/
                    phone_wrongtext.setVisibility(View.VISIBLE);
                }else if(!MyUtils.rexCheckPassword(password)){
                 /*   login_password.setFocusable(true);
                    login_password.setFocusableInTouchMode(true);
                    login_password.requestFocus();
                    login_password.requestFocusFromTouch();*/
                    pass_wrongtext.setVisibility(View.VISIBLE);
                }else{

                    RequestParams params = new RequestParams("https://www.wenzhuoge.xyz/electricity/getApp_password?username="+phonenum+"&password="+password);
                    x.http().get(params, new Callback.CommonCallback<String>() {
                        @Override
                        public void onSuccess(String result) {


                            if(!result.equals("fail")){
                                try {
                                    Gson gson = new Gson();
                                    ulist = gson.fromJson(result, new TypeToken<List<User>>() {
                                    }.getType());


                                } catch (Exception e) {
                                }
                                final User u =ulist.get(0);
                                Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                                final SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("username", u.getUsername());
                                editor.putString("password", u.getPassword());
                                editor.putString("nickname", u.getNickname());
                                 imgname=u.getImgname();
                                 savepath=Environment.getExternalStorageDirectory()+"/electricity//"+ u.getUsername()+".png";

                                if(u.getImgname()==null||u.getImgname().equals("")){
                                    editor.putString("picture", "");
                                    editor.commit();
                                    LoginActivity.this.finish();

                                }else{
                                    if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
// 判断是否已有权限
                                        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                                                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                                            // 申请权限，参数1是当前activity 参数2是我要申请的相关权限（一个String数组）
                                            //参数3是我定义的requestCode，在onRequestPermissionResult（）要用来识别是否我的返回，
                                            ActivityCompat.requestPermissions(LoginActivity.this,new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                                            }, 123);

                                        }else{

                                                    //这里写入子线程需要做的工作
                                                    editor.putString("picture",savepath);
                                                    editor.commit();
                                                    downloadUpdateApk(LoginActivity.this,savepath,"https://www.wenzhuoge.xyz/electricity/img2/"+u.getImgname());



                                        }
                                    }else {

                                                //这里写入子线程需要做的工作
                                                editor.putString("picture",savepath);
                                                editor.commit();
                                                downloadUpdateApk(LoginActivity.this,savepath,"https://www.wenzhuoge.xyz/electricity/img2/"+u.getImgname());


                                    }



                                }





                             /*   LayoutInflater inflater = getLayoutInflater();                             //先获取当前布局的填充器

                                View peopleview = inflater.inflate(R.layout.people, null);   //通过填充器获取另外一个布局的对象

                                LinearLayout login_layout = (LinearLayout) peopleview.findViewById(R.id.login_layout);     //通过另外一个布局对象的findViewById获取其中的控件
                                LinearLayout loginfinish_layout = (LinearLayout) peopleview.findViewById(R.id.loginfinish_layout);
                                TextView nickname_text = (TextView) peopleview.findViewById(R.id.nickname);

                                login_layout.setVisibility(View.GONE);
                                loginfinish_layout.setVisibility(View.VISIBLE);
                                nickname_text.setText(u.getNickname());*/

                             //   LoginActivity.this.finish();
                            }else{

                                pass_wrongtext.setVisibility(View.VISIBLE);
                                pass_wrongtext.setText("用户名不存在或密码错误");
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


        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);

                dialog.setTitle("温馨提示");

                dialog.setMessage("对不起，因为暂时没有短信验证功能，无法通过手机号找回密码，请您联系小编 qq号2639147439找回密码");

                dialog.setCancelable(false);

                dialog.setPositiveButton("确定",null);


                dialog.show();
            }
        });
        enter_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),RegisterActivity.class);
                intent.putExtra("flag","2");
                startActivity(intent);
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // requestCode识别，找到我自己定义的requestCode
        if (requestCode==123) {
            if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //   ActivityCompat.requestPermissions(UserDetailsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

            }else{

                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        downloadUpdateApk(LoginActivity.this,savepath,"https://www.wenzhuoge.xyz/electricity/img2/"+imgname);
                        editor.putString("picture",savepath);
                        editor.commit();
              ;

            }

        }
    }



    public void  downloadUpdateApk(final Context context, String path, String url) {
        final ProgressDialog dialog2 = new ProgressDialog(context);

        // mDownloadUrl为JSON从服务器端解析出来的下载地址
        RequestParams requestParams = new RequestParams(url);
        // 为RequestParams设置文件下载后的保存路径
        requestParams.setSaveFilePath(path);
        // 下载完成后自动为文件命名
        requestParams.setAutoRename(false);
        x.http().get(requestParams, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File result) {
                // Toast.makeText(context, "11111111111", Toast.LENGTH_SHORT).show();
                //Log.i(tag, "下载成功");
                dialog2.dismiss();
                LoginActivity.this.finish();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                // Log.i(tag, "下载失败");
                // Toast.makeText(context, "22222", Toast.LENGTH_SHORT).show();
                dialog2.dismiss();
                LoginActivity.this.finish();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //   Log.i(tag, "取消下载");
                //   Toast.makeText(context, "33333", Toast.LENGTH_SHORT).show();
                  dialog2.dismiss();
                LoginActivity.this.finish();
            }

            @Override
            public void onFinished() {
                //   Log.i(tag, "结束下载");
                Toast.makeText(context, "44444", Toast.LENGTH_SHORT).show();
                dialog2.dismiss();
                LoginActivity.this.finish();
            }

            @Override
            public void onWaiting() {
                // 网络请求开始的时候调用

                //     Toast.makeText(context, "55555", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStarted() {
                // 下载的时候不断回调的方法
                dialog2.show(context,"提示", "正在下载头像");
                //  Toast.makeText(context, "66666", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                // 当前的下载进度和文件总大小
                //  Log.i(tag, "正在下载中......");


            }
        });

    }


}
