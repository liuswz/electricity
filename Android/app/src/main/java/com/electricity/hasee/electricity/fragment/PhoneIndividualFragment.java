package com.electricity.hasee.electricity.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.electricity.hasee.electricity.BaseFragment;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.RecommandActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.phone_individual)
public class PhoneIndividualFragment  extends BaseFragment {

    @ViewInject(R.id.phone_submit)
    private Button phone_submit;

    @ViewInject(R.id.game_group)
    private RadioGroup game_group;
    private double gpu_rate;

    @ViewInject(R.id.front_cam_group)
    private RadioGroup front_cam_group;
    private double front_cam_rate;

    @ViewInject(R.id.back_cam_group)
    private RadioGroup back_cam_group;
    private double back_cam_rate;

    @ViewInject(R.id.size_group)
    private RadioGroup size_group;
    private double size_rate;
//    front_cam_group front_cam_rate back_cam_group back_cam_rate size_group size_rate front_beauty_group front_beauty_rate back_beauty_group back_beauty_rate hand_group hand_rate
    @ViewInject(R.id.front_beauty_group)
    private RadioGroup front_beauty_group;
    private double front_beauty_rate;

    @ViewInject(R.id.back_beauty_group)
    private RadioGroup back_beauty_group;
    private double back_beauty_rate;

    @ViewInject(R.id.hand_group)
    private RadioGroup hand_group;
    private double hand_rate;

    @ViewInject(R.id.screan_group)
    private RadioGroup screan_group;
    private double screan_rate;

    @ViewInject(R.id.battery_group)
    private RadioGroup battery_group;
    private double battery_rate;

    @ViewInject(R.id.charge_group)
    private RadioGroup charge_group;
    private double charge_rate;

    @ViewInject(R.id.brand_group)
    private RadioGroup brand_group;
    private String must_brand;

    @ViewInject(R.id.specail_group)
    private RadioGroup specail_group;
    private String must_specail;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initView();
    }
    void initView(){
        phone_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //
                int game_groupId = game_group.getCheckedRadioButtonId();
                int front_cam_groupId = front_cam_group.getCheckedRadioButtonId();
                int back_cam_groupId = back_cam_group.getCheckedRadioButtonId();
                int size_groupId = size_group.getCheckedRadioButtonId();
                int front_beauty_groupId = front_beauty_group.getCheckedRadioButtonId();
                int back_beauty_groupId = back_beauty_group.getCheckedRadioButtonId();
                int hand_groupId = hand_group.getCheckedRadioButtonId();
                int screan_groupId = screan_group.getCheckedRadioButtonId();
                int battery_groupId = battery_group.getCheckedRadioButtonId();
                int charge_groupId = charge_group.getCheckedRadioButtonId();
                int brand_groupId = brand_group.getCheckedRadioButtonId();
                int specail_groupId = specail_group.getCheckedRadioButtonId();
                if(game_groupId==-1){
                    game_group.setFocusable(true);
                    game_group.setFocusableInTouchMode(true);
                    game_group.requestFocus();
                    game_group.requestFocusFromTouch();
                }else if(front_cam_groupId==-1){
                    front_cam_group.setFocusable(true);
                    front_cam_group.setFocusableInTouchMode(true);
                    front_cam_group.requestFocus();
                    front_cam_group.requestFocusFromTouch();
                }else if(back_cam_groupId==-1){
                    back_cam_group.setFocusable(true);
                    back_cam_group.setFocusableInTouchMode(true);
                    back_cam_group.requestFocus();
                    back_cam_group.requestFocusFromTouch();
                }else if(size_groupId==-1){
                    size_group.setFocusable(true);
                    size_group.setFocusableInTouchMode(true);
                    size_group.requestFocus();
                    size_group.requestFocusFromTouch();
                }else if(front_beauty_groupId==-1){
                    front_beauty_group.setFocusable(true);
                    front_beauty_group.setFocusableInTouchMode(true);
                    front_beauty_group.requestFocus();
                    front_beauty_group.requestFocusFromTouch();
                }else if(back_beauty_groupId==-1){
                    back_beauty_group.setFocusable(true);
                    back_beauty_group.setFocusableInTouchMode(true);
                    back_beauty_group.requestFocus();
                    back_beauty_group.requestFocusFromTouch();
                }else if(hand_groupId==-1){
                    hand_group.setFocusable(true);
                    hand_group.setFocusableInTouchMode(true);
                    hand_group.requestFocus();
                    hand_group.requestFocusFromTouch();
                    //         must_specail
                }else if(screan_groupId==-1){
                    screan_group.setFocusable(true);
                    screan_group.setFocusableInTouchMode(true);
                    screan_group.requestFocus();
                    screan_group.requestFocusFromTouch();
                }else if(battery_groupId==-1){
                    battery_group.setFocusable(true);
                    battery_group.setFocusableInTouchMode(true);
                    battery_group.requestFocus();
                    battery_group.requestFocusFromTouch();
                }else if(charge_groupId==-1){
                    charge_group.setFocusable(true);
                    charge_group.setFocusableInTouchMode(true);
                    charge_group.requestFocus();
                    charge_group.requestFocusFromTouch();
                }else if(brand_groupId==-1){
                    brand_group.setFocusable(true);
                    brand_group.setFocusableInTouchMode(true);
                    brand_group.requestFocus();
                    brand_group.requestFocusFromTouch();
                }else if(specail_groupId==-1){
                    specail_group.setFocusable(true);
                    specail_group.setFocusableInTouchMode(true);
                    specail_group.requestFocus();
                    specail_group.requestFocusFromTouch();
                }else{

                    switch (game_groupId){
                        case R.id.game_group1:
                            gpu_rate=0;
                            break;
                        case R.id.game_group2:
                            gpu_rate=0.3;
                            break;
                        case R.id.game_group3:
                            gpu_rate=1;
                            break;
                    }

                    switch (front_cam_groupId){
                        case R.id.front_cam_group1:
                            front_cam_rate=0;
                            break;
                        case R.id.front_cam_group2:
                            front_cam_rate=1;
                            break;
                        case R.id.front_cam_group3:
                            front_cam_rate=2;
                            break;
                    }
                    switch (back_cam_groupId){
                        case R.id.back_cam_group1:
                            back_cam_rate=0;
                            break;
                        case R.id.back_cam_group2:
                            back_cam_rate=1;
                            break;
                        case R.id.back_cam_group3:
                            back_cam_rate=2;
                            break;
                    }
                    switch (size_groupId){
                        case R.id.size_group1:
                            size_rate=5.9;
                            break;
                        case R.id.size_group2:
                            size_rate=6.6;
                            break;

                    }
                    switch (front_beauty_groupId){
                        case R.id.front_beauty_group1:
                            front_beauty_rate=0;
                            break;
                        case R.id.front_beauty_group2:
                            front_beauty_rate=1;
                            break;

                    }
                    switch (back_beauty_groupId){
                        case R.id.back_beauty_group1:
                            back_beauty_rate=0;
                            break;
                        case R.id.back_beauty_group2:
                            back_beauty_rate=1;
                            break;

                    }


                    switch (hand_groupId){
                        case R.id.hand_group1:
                            hand_rate=0;
                            break;
                        case R.id.hand_group2:
                            hand_rate=1;
                            break;

                    }

                    switch (screan_groupId){
                        case R.id.screan_group1:
                            screan_rate=0.5;
                            break;
                        case R.id.screan_group2:
                            screan_rate=1;
                            break;

                    }
//
                    switch (battery_groupId){
                        case R.id.battery_group1:
                            battery_rate=0;
                            break;
                        case R.id.battery_group2:
                            battery_rate=1;
                            break;

                    }

                    switch (charge_groupId){
                        case R.id.charge_group1:
                            charge_rate=0;
                            break;
                        case R.id.charge_group2:
                            charge_rate=1;
                            break;

                    }
//
//
                    switch (brand_groupId){
                        case R.id.brand_group1:
                            must_brand="";
                            break;
                        case R.id.brand_group2:
                            must_brand="小米";
                            break;
                        case R.id.brand_group3:
                            must_brand="荣耀";
                            break;
                        case R.id.brand_group4:
                            must_brand="华为";
                            break;
                        case R.id.brand_group5:
                            must_brand="苹果";
                            break;
                        case R.id.brand_group6:
                            must_brand="魅族";
                            break;
                        case R.id.brand_group7:
                            must_brand="oppo";
                            break;
                        case R.id.brand_group8:
                            must_brand="vivo";
                            break;

                    }

                    switch (specail_groupId){
                        case R.id.specail_group1:
                            must_specail="";
                            break;
                        case R.id.specail_group2:
                            must_specail="nfc";
                            break;
                        case R.id.specail_group3:
                            must_specail="屏幕指纹解锁";
                            break;
                        case R.id.specail_group4:
                            must_specail="红外线遥控";
                            break;
                        case R.id.specail_group5:
                            must_specail="无线充电";
                            break;
                        case R.id.specail_group6:
                            must_specail="防水";
                            break;
                        case R.id.specail_group7:
                            must_specail="扩展sd储存卡";
                            break;
                        case R.id.specail_group8:
                            must_specail="3D结构光";
                            break;
                    }


                    Intent intent = new Intent(x.app(),RecommandActivity.class);
                    //
                    intent.putExtra("gpu_rate",gpu_rate+"");
                    intent.putExtra("front_cam_rate",front_cam_rate+"");
                    intent.putExtra("back_cam_rate",back_cam_rate+"");
                    intent.putExtra("size_rate",size_rate+"");
                    intent.putExtra("front_beauty_rate",front_beauty_rate+"");
                    intent.putExtra("back_beauty_rate",back_beauty_rate+"");
                    intent.putExtra("hand_rate",hand_rate+"");
                    intent.putExtra("screan_rate",screan_rate+"");
                    intent.putExtra("battery_rate",battery_rate+"");
                    intent.putExtra("charge_rate",charge_rate+"");
                    intent.putExtra("must_brand",must_brand);
                    intent.putExtra("must_specail",must_specail);

                    startActivity(intent);
                }



            }
        });




    }


}
//   gpu_rate front_cam_rate  back_cam_rate size_rate front_beauty_rate back_beauty_rate hand_rate screan_rate battery_rate charge_rate must_brand must_specail
//screan_group screan_rate battery_group battery_rate charge_group charge_rate brand_group must_brand specail_group must_specail