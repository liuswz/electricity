package com.electricity.hasee.electricity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.entity.Phone;

import org.xutils.x;

import java.text.DecimalFormat;
import java.util.List;

public class IndividualRandRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Phone> data;
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局
    static final int TYPE_FOOTER = 1;
    //    //上拉加载更多
//    static final int PULL_LOAD_MORE = 0;
    //正在加载更多
    static final int LOADING_MORE = 1;
    //没有更多
    static final int NO_MORE = 2;
    //脚布局当前的状态,默认为没有更多
    int footer_state =1;
    int status=0;

    double gpu_rate, front_cam_rate, back_cam_rate, size_rate, front_beauty_rate, back_beauty_rate, hand_rate, screan_rate, battery_rate,charge_rate;
    public IndividualRandRecyclerViewAdapter(Context context,List<Phone> data,int status,double gpu_rate,double front_cam_rate,double back_cam_rate,double size_rate,
                                             double front_beauty_rate,double back_beauty_rate,double hand_rate,double screan_rate,double battery_rate,
                                             double charge_rate){

        this.mContext = context;

        this.data = data;
        this.status =  status;

        this.gpu_rate =  gpu_rate;
        this.front_cam_rate =  front_cam_rate;
        this.back_cam_rate =  back_cam_rate;
        this.size_rate =  size_rate;
        this.front_beauty_rate =  front_beauty_rate;
        this.back_beauty_rate =  back_beauty_rate;
        this.screan_rate =  screan_rate;
        this.battery_rate =  battery_rate;
        this.hand_rate =  hand_rate;
        this.charge_rate =  charge_rate;


    }


    IndividualRandRecyclerViewAdapter.FootViewHolder f=null;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rank_item, parent,
                    false);
            return new IndividualRandRecyclerViewAdapter.ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = View.inflate(mContext, R.layout.item_foot, null);
            IndividualRandRecyclerViewAdapter.FootViewHolder footViewHolder = new IndividualRandRecyclerViewAdapter.FootViewHolder(view);
            return footViewHolder;
        }
        return null;



    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof IndividualRandRecyclerViewAdapter.ItemViewHolder){
            IndividualRandRecyclerViewAdapter.ItemViewHolder newHolder = (IndividualRandRecyclerViewAdapter.ItemViewHolder) holder;
            newHolder.name.setText(data.get(position).getName());
            //  newHolder.cpu_appearance.setText(data.get(position).getCpu()+"   "+data.get(position).getFront_beauty());
            //  holder.image.setImageURI(null);
            newHolder.self_comment.setText(data.get(position).getSelf_comment());
            newHolder.sequence.setText(position+1+"");

            Glide.with(x.app()).load("https://www.wenzhuoge.xyz/electricity/img/"+data.get(position).getPicture())
                    .into(newHolder.image);
            newHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Intent intent = new Intent(mContext,DetailActivity.class);
                    intent.putExtra("id",data.get(position).getId()+"");
                    mContext.startActivity(intent);

                }

            }); //Total Common Game Camera XuHang Xiaomi Honor Huawei IPhone Oppo Vivo Meizu
            DecimalFormat df = new DecimalFormat("#.0");
            double sum= (data.get(position).getUi_num()+data.get(position).getCpu_num()+data.get(position).getAi_num()+data.get(position).getRom_num()+(data.get(position).getGpu_num()*gpu_rate)+(data.get(position).getFront_cam_num()*front_cam_rate)+(data.get(position).getBack_cam_num()*back_cam_rate)+(data.get(position).getFront_beauty_num()*front_beauty_rate)+
                    (data.get(position).getBack_beauty_num()*back_beauty_rate)+(data.get(position).getHand_num()*hand_rate)+(data.get(position).getScreen_num()*screan_rate)+
                    (data.get(position).getConsume_power_num()*battery_rate)+(+data.get(position).getCharge_num()*charge_rate)  )/(data.get(position).getPrice1()-data.get(position).getDifferent_price());
            newHolder.mark_text.setText(df.format(sum)+"分");



        }else{


            IndividualRandRecyclerViewAdapter.FootViewHolder footViewHolder = (IndividualRandRecyclerViewAdapter.FootViewHolder) holder;
            if(footer_state==LOADING_MORE&&status==0){
                footViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                footViewHolder.tv_line1.setVisibility(View.GONE);
                footViewHolder.tv_line2.setVisibility(View.GONE);
                footViewHolder.tv_state.setText("正在加载...");
            }else if(footer_state==NO_MORE||status==1){
                footViewHolder.mProgressBar.setVisibility(View.GONE);
                footViewHolder.tv_line1.setVisibility(View.VISIBLE);
                footViewHolder.tv_line2.setVisibility(View.VISIBLE);
                footViewHolder.tv_state.setText("没有啦");
            }
              /*  switch (footer_state) {//根据状态来让脚布局发生改变
//                case PULL_LOAD_MORE://上拉加载
//                    footViewHolder.mProgressBar.setVisibility(View.GONE);
//                    footViewHolder.tv_state.setText("上拉加载更多");
//                    break;

                    case LOADING_MORE:
                       // Toast.makeText(x.app(), "1", Toast.LENGTH_SHORT).show();
                        footViewHolder.mProgressBar.setVisibility(View.VISIBLE);
                        footViewHolder.tv_line1.setVisibility(View.GONE);
                        footViewHolder.tv_line2.setVisibility(View.GONE);
                        footViewHolder.tv_state.setText("正在加载...");
                        break;
                    case NO_MORE:

                        footViewHolder.mProgressBar.setVisibility(View.GONE);
                        footViewHolder.tv_line1.setVisibility(View.VISIBLE);
                        footViewHolder.tv_line2.setVisibility(View.VISIBLE);
                        footViewHolder.tv_state.setText("我是有底线的");
                        footViewHolder.tv_state.setTextColor(Color.parseColor("#ff00ff"));
                        break;
                }*/

        }






    }



    @Override

    public int getItemCount() {

        return data.size() == 0 ? 0 : data.size()+1;

    }
    @Override
    public int getItemViewType(int position) {
        //如果position加1正好等于所有item的总和,说明是最后一个item,将它设置为脚布局
        if (position+1 == getItemCount()) {
            //  Toast.makeText(mContext, position+"----", Toast.LENGTH_SHORT).show();
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{


        private TextView sequence;
        private TextView name;
        // private TextView cpu_appearance;
        private TextView self_comment;
        private ImageView image;
        private TextView mark_text;

        ItemViewHolder(View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            //     cpu_appearance = itemView.findViewById(R.id.cpu_appearance);
            self_comment = itemView.findViewById(R.id.self_comment);
            sequence= itemView.findViewById(R.id.sequence);
            mark_text=itemView.findViewById(R.id.mark_text);
        }


    }
    public static class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;
        private TextView tv_state;
        private TextView tv_line1;
        private TextView tv_line2;


        public FootViewHolder(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
            tv_state = (TextView) itemView.findViewById(R.id.foot_view_item_tv);
            tv_line1 = (TextView) itemView.findViewById(R.id.tv_line1);
            tv_line2 = (TextView) itemView.findViewById(R.id.tv_line2);

        }
    }

    /**
     * 改变脚布局的状态的方法,在activity根据请求数据的状态来改变这个状态
     *
     * @param state
     */
    public void changeState(int state) {
        this.footer_state = state;
        notifyDataSetChanged();
    }


}

