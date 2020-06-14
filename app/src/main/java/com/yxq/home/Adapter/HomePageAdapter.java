package com.yxq.home.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxq.home.Activity.ListItemActivity.ListItemActivity;
import com.yxq.home.Model.HomePageItem;
import com.yxq.home.R;

import java.util.List;

/**
 * Created by 残 on 2019/11/24.
 */

public class HomePageAdapter extends RecyclerView.Adapter<HomePageAdapter.ViewHolder> {
    //数据
    private List<HomePageItem> list;


    //构造函数
    public HomePageAdapter() {
    }

    public HomePageAdapter(List<HomePageItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定模板
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final HomePageItem homePageItem = list.get(position);

        holder.nameView.setText(homePageItem.getItemName());
        holder.likeView.setText(homePageItem.getItemLike());

        //设置点击事件

        //点击名字跳转
        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置跳转的视图
                Intent listItemIntent = new Intent(v.getContext(), ListItemActivity.class);
                listItemIntent.putExtra("url", homePageItem.getIntendUrl());
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
        TextView nameView;
        TextView likeView;
        //内部类构造函数
        public ViewHolder(View view) {
            super(view);
            nameView = (TextView) view.findViewById(R.id.homepage_item_name);
            likeView = (TextView) view.findViewById(R.id.homepage_item_like);
        }
    }
}
