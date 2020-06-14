package com.yxq.home.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxq.home.Activity.ListItemActivity.ListItemActivity;
import com.yxq.home.Model.ListItem;
import com.yxq.home.R;

import java.util.List;


/**
 * Created by 残 on 2019/11/23.
 */

public class ListItemAdapter  extends RecyclerView.Adapter<ListItemAdapter.ViewHolder>{
    //数据
    private List<ListItem> list;


    //构造函数
    public ListItemAdapter() {
    }

    public ListItemAdapter(List<ListItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定模板
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ListItem listItem = list.get(position);

        holder.imageView.setImageResource(listItem.getListItemImg());
        holder.titleView.setText(listItem.getListItemTitle());
        holder.timeView.setText(listItem.getListItemTime());

        //设置点击事件

        //点击名字跳转
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转的视图
                Intent listItemIntent = new Intent(v.getContext(), ListItemActivity.class);
                listItemIntent.putExtra("url", listItem.getIntendUrl());
                listItemIntent.putExtra("title", "文章内容");
                //跳转
                v.getContext().startActivity(listItemIntent);
            }
        });

        //点击头像跳转
        holder.titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转的视图
                Intent listItemIntent = new Intent(v.getContext(), ListItemActivity.class);
                listItemIntent.putExtra("url", listItem.getIntendUrl());
                listItemIntent.putExtra("title", "文章内容");
                //跳转
                v.getContext().startActivity(listItemIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //内部类
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleView;
        TextView timeView;
        //内部类构造函数
        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.list_item_Img);
            titleView = (TextView) view.findViewById(R.id.list_item_title);
            timeView = (TextView) view.findViewById(R.id.list_item_time);
        }
    }
}
