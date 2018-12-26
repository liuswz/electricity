package com.electricity.hasee.electricity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.entity.Phone;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class CollectionListViewAdapter extends BaseSwipeAdapter {


    private Context mContext;
    private List<Phone> data;
    private SwipeLayout mSwipeLayout;
    private String username;
    private String deleteurl="https://www.wenzhuoge.xyz/electricity/setApp_deleteCollection?username=";
    //    //上拉加载更多
    //脚布局当前的状态,默认为没有更多

    public CollectionListViewAdapter(Context context,List<Phone> data,String username){

        this.mContext = context;
        this.data = data;
        this.username = username;

    }




    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    /**
     * 此方法中一定不能绑定监听器和填充数据
     * never bind listeners or fill values, just genertate view here !!
     * just generate view
     *
     * @param position
     * @param parent
     * @return
     */
    @Override
    public View generateView(final int position, ViewGroup parent) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.collection_item, null);
        return v;
    }

    @Override
    public void fillValues(final int position, View convertView) {

        Phone p = data.get(position);

        //填充数据
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(p.getName());

        TextView self_comment = (TextView) convertView.findViewById(R.id.self_comment);
        self_comment.setText(p.getSelf_comment());
        ImageView image = (ImageView)convertView.findViewById(R.id.image);
        Glide.with(x.app()).load("https://www.wenzhuoge.xyz/electricity/img/"+p.getPicture())
                .into(image);


        mSwipeLayout = (SwipeLayout) convertView.findViewById(getSwipeLayoutResourceId(position));
        //绑定监听事件
        mSwipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.tv_cancel_favorite));
            }
        });

        /**
         * 用getSurfaceView()可以防止滑回与点击事件冲突
         */
        mSwipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//跳转到充电站详情

                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("id",data.get(position).getId()+"");
                mContext.startActivity(intent);
            }
        });

        convertView.findViewById(R.id.tv_cancel_favorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//bottomView点击事件 取消收藏站点

            //    Phone p  = data.get(position);
                //Log.d("favoriteActivity", "favoriteActivity--cancelStationName--->" + favoriteStation.getStationName());
                delete_collection(position);
            }
        });
    }

    @Override
    public int getCount() {
        Log.d("dataListSize", "dataListSize--->" + data.size());
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void delete_collection( final int position){
        Phone p =data.get(position);
        RequestParams params = new RequestParams(deleteurl+username+"&type=phone&product_id="+p.getId());
        //   list2 = new ArrayList<Phone>();

        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override

            public void onSuccess(String result) {
              if(result.equals("success")){
                  data.remove(position);
                  mSwipeLayout.close();
                  notifyDataSetChanged();
                  Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
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

