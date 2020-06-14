package com.yxq.home.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yxq.home.Model.UserItem;
import com.yxq.home.R;

import java.util.List;

/**
 * 用户个人信息界面适配器
 */
//继承内部类
public class UserItemAdapter extends RecyclerView.Adapter<UserItemAdapter.ViewHolder>{


    //数据
    private List<UserItem> list;

    //接收数据
    private Intent intent;

    //构造函数

    public UserItemAdapter() {
    }

    public UserItemAdapter(List<UserItem> list) {
        this.list = list;
    }

    public UserItemAdapter(List<UserItem> list, Intent intent) {
        this.list = list;
        this.intent = intent;
    }

    //创建视图并初始化
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //绑定模板
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //绑定事件
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final UserItem userItem = list.get(position);

        holder.iconView.setImageResource(userItem.getUserItemIcon());
        holder.nameView.setText(userItem.getUserItemName());

        //设置点击事件

        //点击名字跳转
        holder.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳窗提示

                //设置跳转的视图
                Intent userItemIntent = new Intent(v.getContext(),userItem.getIntendUrl());

                //添加登陆之后的信息
                String id = intent.getStringExtra("id");
                userItemIntent.putExtra("id",id);

                //跳转
                v.getContext().startActivity(userItemIntent);
            }
        });

        //点击头像跳转
        holder.iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳窗提示

                //设置跳转的视图
                Intent userItemIntent = new Intent(v.getContext(),userItem.getIntendUrl());

                //添加登陆之后的信息
                String id = intent.getStringExtra("id");
                userItemIntent.putExtra("id",id);

                //跳转
                v.getContext().startActivity(userItemIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    //内部类
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconView;
        TextView nameView;

        //内部类构造函数
        public ViewHolder(View view) {
            super(view);
            iconView = (ImageView) view.findViewById(R.id.user_item_icon);
            nameView = (TextView) view.findViewById(R.id.user_item_name);
        }
    }

}
