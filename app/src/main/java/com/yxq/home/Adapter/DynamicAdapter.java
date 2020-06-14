package com.yxq.home.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxq.home.Model.DynamicItem;
import com.yxq.home.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Yeung on 2020/5/26.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {

    //数据
    private List<DynamicItem> list;

    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:SS");

    public DynamicAdapter() {
    }

    public DynamicAdapter(List<DynamicItem> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DynamicAdapter.ViewHolder holder, int position) {
        final DynamicItem dynamicItem = list.get(position);

        holder.textViewDate.setText(sdfDate.format(dynamicItem.getCreateTime()));
        holder.textViewContext.setText(dynamicItem.getMessage());
        holder.textViewTime.setText(sdfTime.format(dynamicItem.getCreateTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    //内部类
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDate;
        TextView textViewContext;
        TextView textViewTime;
        //内部类构造函数
        public ViewHolder(View view) {
            super(view);
            textViewDate = (TextView) view.findViewById(R.id.dynamic_textViewDate);
            textViewContext = (TextView) view.findViewById(R.id.dynamic_textViewContext);
            textViewTime = (TextView) view.findViewById(R.id.dynamic_textViewTime);
        }
    }
}
