package com.electricity.hasee.electricity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.electricity.hasee.electricity.XUtil.MyUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class UserDetailsActivity extends AppCompatActivity {
    @ViewInject(R.id.ud_back)
    private ImageButton ud_back;
    @ViewInject(R.id.ud_touxiang)
    private ImageButton ud_touxiang;
    @ViewInject(R.id.ud_usernametext)
    private TextView ud_usernametext;
    @ViewInject(R.id.ud_nicknametext)
    private TextView ud_nicknametext;

    @ViewInject(R.id.ud_nicknamelayout)
    private LinearLayout ud_nicknamelayout;
    private String username=null;
    Bitmap bitmap;
    private File file;

    private String upload_url="https://www.wenzhuoge.xyz/electricity/setApp_picture?username=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        x.view().inject(this);
        SharedPreferences sharedPreferences = x.app().getSharedPreferences("user", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
        file = new File(Environment.getExternalStorageDirectory(), username+".png");


        ud_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsActivity.this.finish();
            }
        });

        ud_nicknamelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(x.app(),UpdateNickNameActivity.class);
                startActivity(intent);
            }
        });
        ud_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
// 判断是否已有权限
                    if (ContextCompat.checkSelfPermission(UserDetailsActivity.this,
                            Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                        // 申请权限，参数1是当前activity 参数2是我要申请的相关权限（一个String数组）
                        //参数3是我定义的requestCode，在onRequestPermissionResult（）要用来识别是否我的返回，
                        ActivityCompat.requestPermissions(UserDetailsActivity.this,new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                               }, 123);
                    }
                }else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intent, 2);

                }
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = x.app().getSharedPreferences("user", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", null);//(key,若无数据需要赋的值)
        String nickname = sharedPreferences.getString("nickname", null);
        String picture = sharedPreferences.getString("picture", null);
        // String password = sharedPreferences.getString("password", null);

        ud_usernametext.setText(username);
        ud_nicknametext.setText(nickname);
        RequestOptions options = new RequestOptions().circleCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
      //  Toast.makeText(this, picture, Toast.LENGTH_SHORT).show();
        Glide.with(UserDetailsActivity.this).load(picture) .apply(options)
                .into(ud_touxiang);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //resultCode结果返回码，如果返回RESULT_OK说明操作正常
        if (resultCode!=RESULT_OK) {
            return;
        }
        //判断是否有SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_UNKNOWN)) {
            Toast.makeText(this, "SD卡不可用", Toast.LENGTH_SHORT).show();
            return;
        }
        //压缩图片的采样率，这里直接设定了，还有一种方法是根据屏幕计算
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=false;
        options.inSampleSize=2;
        switch (requestCode) {
            case 1:

            case 2:
                if (data==null) {
                    return;
                }
                Uri uri=data.getData();
                Cursor c=getContentResolver().query(uri, null, null, null, null);
                if (c.moveToNext()) {
                    String path=c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    Log.i("path", path);
                   // file=new File(path);
                 /*   if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                        //权限还没有授予，需要在这里写申请权限的代码
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_OPEN_ALBUM);
                    }else {
                        //权限已经被授予，在这里直接写要执行的相应方法即可

                    }*/


                    bitmap=BitmapFactory.decodeFile(path,options);
                  //  bitmap=MyUtils.toRoundBitmap(bitmap);
                    //这里曾经尝试过使用 oldfile.renameTo(new File(newpath))这种方法复制图片 ，有的时候可以运行，但是大多时候失败，网上查原因貌似是说和存储的形式有关，基本上就是看人品了。所以只能把图片另存处理了
                 //   saveMyBitmap(bitmap,file.getName());
                }
                break;
        }

    //    Toast.makeText(this, file.getPath()+"****", Toast.LENGTH_SHORT).show();

        saveMyBitmap(bitmap);

    }

    public void saveMyBitmap(Bitmap mBitmap)  {
        //定义另存文件的路径和名字、
      /*  file= new File( Environment.getExternalStorageDirectory()+"/electricity/",//后面加一个文件夹是为了避免把图片直接存在SD卡的大目录下，没来得及运行是否可以，貌似可以，不成的话就删了
                username+".png");*/
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

      /*  if (ActivityCompat.checkSelfPermission(UserDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(UserDetailsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }
*/
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
     //   ud_touxiang.setImageBitmap(null);//这个方法是网上看到的，说是可以在 重复操作的时候避免bitmap加载不上去
       // ud_touxiang.setu
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*   RequestOptions options = new RequestOptions().circleCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
       Glide.with(x.app()).load(Environment.getExternalStorageDirectory()+"/"+
                username+".png")  .apply(options)
                .into(ud_touxiang);*/

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("picture", Environment.getExternalStorageDirectory()+"/"+
                username+".png");
        editor.commit();
        upload();
    }

    private void upload(){
        String path=Environment.getExternalStorageDirectory()+"/"+ username+".png";
        RequestParams params = new RequestParams(upload_url+username);
        params.setMultipart(true);
        final ProgressDialog dialog2 = new ProgressDialog(UserDetailsActivity.this).show(UserDetailsActivity.this,"提示", "正在上传");
        params.addBodyParameter("picture",new File(path));//设置上传的文件路径
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                dialog2.dismiss();
                if(result.equals("success")){
                    Toast.makeText(UserDetailsActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                dialog2.dismiss();
            }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // requestCode识别，找到我自己定义的requestCode
        if (requestCode==123) {
            if (ActivityCompat.checkSelfPermission(UserDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

             //   ActivityCompat.requestPermissions(UserDetailsActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

            }else{
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
            }

        }
    }
}
