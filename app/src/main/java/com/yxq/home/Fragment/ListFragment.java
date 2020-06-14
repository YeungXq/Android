package com.yxq.home.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yxq.home.Adapter.ListItemAdapter;
import com.yxq.home.Model.ListItem;
import com.yxq.home.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 残 on 2019/11/23.
 */

public class ListFragment extends Fragment {
    //ListItem 数据
    private List<ListItem> listItemList = new ArrayList<>();

    //定义view用来设置fragment的layout
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取view用来设置fragment的layout
        view = inflater.inflate(R.layout.fragment_list, container, false);
        initListItemData();  //初始化ListItemAdapter数据
        initRecyclerView();  //初始化RecyclerView 并发送数据与视图

        return view;
    }

    //初始化RecyclerView
    private void initRecyclerView() {
        //获取recyclerView
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_item_recycleView);
        //实例化线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        //设置listItemAdapter适配器
        ListItemAdapter listItemAdapter = new ListItemAdapter(listItemList);
        //设置布局
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        recyclerView.setAdapter(listItemAdapter);


    }

    //初始化listItemAdapter数据
    private void initListItemData() {
        if (listItemList.size() == 0) {
            ListItem listItem1 = new ListItem(R.drawable.article1, "Python2爬虫学习系列教程", "2019-11-23", "https://cuiqingcai.com/1052.html");
            listItemList.add(listItem1);
            ListItem listItem2 = new ListItem(R.drawable.article2, "七分钟全面了解位运算", "2019-11-23", "https://cuiqingcai.com/6841.html");
            listItemList.add(listItem2);
            ListItem listItem3 = new ListItem(R.drawable.article3, "Python3爬虫视频学习教程", "2019-11-23", "https://cuiqingcai.com/4320.html");
            listItemList.add(listItem3);
            ListItem listItem4 = new ListItem(R.drawable.article4, "Python爬虫入门一之综述", "2019-11-23", "https://cuiqingcai.com/927.html");
            listItemList.add(listItem4);
            ListItem listItem5 = new ListItem(R.drawable.article5, "Python入门Urllib库的使用", "2019-11-23", "https://cuiqingcai.com/947.html");
            listItemList.add(listItem5);
            ListItem listItem6 = new ListItem(R.drawable.article6, "Python3网络爬虫实战教程", "2019-11-23", "https://cuiqingcai.com/5052.html");
            listItemList.add(listItem6);
        }

    }
}
