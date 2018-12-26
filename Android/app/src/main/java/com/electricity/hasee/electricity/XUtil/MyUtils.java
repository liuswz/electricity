package com.electricity.hasee.electricity.XUtil;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.electricity.hasee.electricity.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

public class MyUtils {

    public static int String_length(String value) {
        int valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 2;
            } else {
                valueLength += 1;
            }
        }
        return valueLength;
    }
    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
    public static boolean rexCheckPassword(String input) {
        // 6-20 位，字母、数字、字符
        //String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
        String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,20}$";
        return input.matches(regStr);
    }


    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));

                //底部导航栏
                //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }



    public static void  downloadUpdateApk(final Context context, String path, String url) {
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

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                   // Log.i(tag, "下载失败");
                   // Toast.makeText(context, "22222", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
                }

                @Override
                public void onCancelled(CancelledException cex) {
                 //   Log.i(tag, "取消下载");
                 //   Toast.makeText(context, "33333", Toast.LENGTH_SHORT).show();
                  //  dialog2.dismiss();
                }

                @Override
                public void onFinished() {
                 //   Log.i(tag, "结束下载");
                    Toast.makeText(context, "44444", Toast.LENGTH_SHORT).show();
                    dialog2.dismiss();
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
