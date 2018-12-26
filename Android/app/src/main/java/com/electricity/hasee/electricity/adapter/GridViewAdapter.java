package com.electricity.hasee.electricity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.electricity.hasee.electricity.DetailActivity;
import com.electricity.hasee.electricity.R;
import com.electricity.hasee.electricity.entity.Phone;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private List<Phone> list;

   // private LayoutInflater mInflater;
    private Context mContext;


    public GridViewAdapter(Context context, List<Phone>  mList) {
        list = mList;

        mContext = context;
     //   mInflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
    /*    View gridview_item = View.inflate(mContext, R.layout.brand_item, null);
     *//*   View view = LayoutInflater.from(mContext).inflate(R.layout.brand_item, parent,
                false);*//*

        Glide.with(mContext).load("https://www.wenzhuoge.xyz/electricity/img/"+list.get(position).getPicture())
                .into(brand_image);
        brand_name.setText(list.get(position).getName());
        brand_comment.setText(list.get(position).getSelf_comment());

        return gridview_item;*/


        ItemViewTag viewTag;

        if (convertView == null)
        {
          //  convertView =View.inflate(mContext, R.layout.brand_item, null);
            convertView= LayoutInflater.from(mContext).inflate(R.layout.brand_item, null
                    );
            // construct an item tag
            viewTag=new ItemViewTag((ImageView) convertView.findViewById(R.id.brand_image),(TextView) convertView.findViewById(R.id.brand_name)) ;
            convertView.setTag(viewTag);
          //  Toast.makeText(mContext, "111111111111111111", Toast.LENGTH_SHORT).show();
     //       convertView.setTag(viewTag);
        }else{
            viewTag = (ItemViewTag) convertView.getTag();
        }
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                mContext.startActivity(intent);

            }

        });
        Glide.with(mContext).load("https://www.wenzhuoge.xyz/electricity/img/"+list.get(position).getPicture())
                .into(viewTag.brand_image);
        viewTag.brand_name.setText(list.get(position).getName());
      //  viewTag.brand_comment.setText(list.get(position).getSelf_comment());

        return convertView;
    }

    class ItemViewTag
    {
        private ImageView brand_image;
        private TextView brand_name;
     //   private TextView brand_comment;

        public ItemViewTag(ImageView icon, TextView name)
        {
         //   this.brand_comment = comment;
            this.brand_name = name;
            this.brand_image = icon;
        }
    }
}
