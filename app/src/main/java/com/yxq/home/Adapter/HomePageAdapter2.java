package com.yxq.home.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxq.home.Activity.ListItemActivity.ListItemActivity;
import com.yxq.home.Model.HomePageItem2;
import com.yxq.home.R;

import java.util.List;


/**
 * Created by 残 on 2019/11/24.
 */

public class HomePageAdapter2  extends RecyclerView.Adapter<HomePageAdapter2.ViewHolder>{
    //数据
    private List<HomePageItem2> list;


    //构造函数
    public HomePageAdapter2() {
    }

    public HomePageAdapter2(List<HomePageItem2> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定模板
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item2,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomePageItem2 homePageItem2 = list.get(position);

        holder.imgView.setImageResource(homePageItem2.getHomePageImg());
        holder.titleView.setText(homePageItem2.getHomePageTitle());
        holder.timeView.setText(homePageItem2.getHomePageTime());

        //设置点击事件

        //点击名字跳转
        holder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转的视图
                Intent listItemIntent = new Intent(v.getContext(), ListItemActivity.class);
                listItemIntent.putExtra("url", homePageItem2.getIntendUrl());
                listItemIntent.putExtra("title", "文章内容");
                //跳转
                v.getContext().startActivity(listItemIntent);
            }
        });
        holder.timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转的视图
                Intent listItemIntent = new Intent(v.getContext(), ListItemActivity.class);
                listItemIntent.putExtra("url", homePageItem2.getIntendUrl());
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
        ImageView imgView;
        TextView titleView;
        TextView timeView;
        //内部类构造函数
        public ViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.homepage_item_img);
            titleView = (TextView) view.findViewById(R.id.homepage_item_title);
            timeView = (TextView) view.findViewById(R.id.homepage_item_time);
        }
    }
}
