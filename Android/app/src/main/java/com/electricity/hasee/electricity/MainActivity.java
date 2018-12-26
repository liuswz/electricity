package com.electricity.hasee.electricity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.electricity.hasee.electricity.XUtil.MyUtils;
import com.electricity.hasee.electricity.fragment.IndividualityFragment;
import com.electricity.hasee.electricity.fragment.MainpageFragment;
import com.electricity.hasee.electricity.fragment.PeopleFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {



    @ViewInject(R.id.rg_radio_navigation)
    private RadioGroup mRadioGroup;

  //  private Fragment[] mFragments;
    @ViewInject(R.id.fl_radio_show)
    private FrameLayout mLayout;

    @ViewInject(R.id.rb_radio_homepage)
    private RadioButton rb_radio_homepage;
    @ViewInject(R.id.rb_radio_individuality)
    private RadioButton rb_radio_individuality;
    @ViewInject(R.id.rb_radio_people)
    private RadioButton rb_radio_people;

    private MainpageFragment f1;
    private IndividualityFragment f2;
    private PeopleFragment f3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);

      //  search=(TextView)this.findViewById(R.id.search);
        initFragment();
        setListener();

    }

    @Override

    public void finish() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

        dialog.setTitle("温馨提示");

        dialog.setMessage("是否退出本程序？");

        dialog.setCancelable(false);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                System.exit(0);

            }

        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                Toast.makeText(MainActivity.this, "谢谢支持", Toast.LENGTH_LONG).show();

            }

        });

        dialog.show();

    }

    private void setListener() {

        //对RadioGroup设置监听事件(监听点击选择)

        mRadioGroup.setOnCheckedChangeListener(this);



    }


    private void initFragment() {

        //初始化要显示的Fragment数组

//        mFragments=new Fragment[3];
//
//        mFragments[0]=new MainpageFragment();
//
//        mFragments[1]=new IndividualityFragment();
//
//        mFragments[2]=new PeopleFragment();

      //  MyUtils.setWindowStatusBarColor(this,R.color.white);
      //  MyUtils.setAndroidNativeLightStatusBar(this,true);

        //获取Fragment管理器

        FragmentManager manager=getSupportFragmentManager();

        //获取事物(使用v4包下)

        FragmentTransaction transaction=manager.beginTransaction();

        //默认选中HomepageFragment替换Framelayout
        f1 = new MainpageFragment();

        transaction.add(R.id.fl_radio_show,f1);

        //提交事物

        transaction.commit();

        //默认点击首页

        mRadioGroup.check(R.id.rb_radio_homepage);

    }


    @Override

    public void onCheckedChanged(RadioGroup group,  int checkedId) {

        //写法与默认点击页面的相同

        FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction  transaction=manager.beginTransaction();
        hideAllFragment(transaction);
        switch(checkedId){

            case R.id.rb_radio_homepage:

                if(f1==null){
                    f1 = new MainpageFragment();
                    transaction.add(R.id.fl_radio_show,f1);
                }else{
                    transaction.show(f1);
                }
                break;




            case R.id.rb_radio_individuality:

                if(f2==null){
                    f2 = new IndividualityFragment();
                    transaction.add(R.id.fl_radio_show,f2);
                }else{
                    transaction.show(f2);
                }

                break;

            case R.id.rb_radio_people:

                if(f3==null){
                    f3 = new PeopleFragment();
                    transaction.add(R.id.fl_radio_show,f3);
                }else{
                    transaction.show(f3);
                }

                break;



        }

        transaction.commit();



    }


    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }

    }


}
