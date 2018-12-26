package com.electricity.hasee.electricity.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.entity.Phone;

import org.xutils.x;

import java.util.List;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Phone> data;
    //普通布局的type
    static final int TYPE_ITEM = 0;
    //脚布局

    //    //上拉加载更多
    //脚布局当前的状态,默认为没有更多

    public SearchRecyclerViewAdapter(Context context,List<Phone> data){

        this.mContext = context;

        this.data = data;



    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent,
                    false);
            return new ItemViewHolder(view);




    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


            ItemViewHolder newHolder = (ItemViewHolder) holder;
            newHolder.name.setText(data.get(position).getName());
       //     newHolder.cpu_appearance.setText(data.get(position).getCpu()+"   "+data.get(position).getFront_beauty());
            //  holder.image.setImageURI(null);
           newHolder.self_comment.setText(data.get(position).getSelf_comment());


            Glide.with(x.app()).load("https://www.wenzhuoge.xyz/electricity/img/"+data.get(position).getPicture())
                    .into(newHolder.image);
            newHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override

                public void onClick(View v) {

                    Intent intent = new Intent(mContext,DetailActivity.class);
                    intent.putExtra("id",data.get(position).getId()+"");
                    mContext.startActivity(intent);

                }

            });


    }



    @Override

    public int getItemCount() {

        return data.size() == 0 ? 0 : data.size();

    }
    @Override
    public int getItemViewType(int position) {
        //如果position加1正好等于所有item的总和,说明是最后一个item,将它设置为脚布局

            return TYPE_ITEM;

    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
      //  private TextView cpu_appearance;
        private TextView self_comment;
        private ImageView image;


        ItemViewHolder(View itemView) {

            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
        //    cpu_appearance = itemView.findViewById(R.id.cpu_appearance);
            self_comment = itemView.findViewById(R.id.self_comment);

        }


    }



}

