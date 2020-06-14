package com.yxq.home.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yxq.home.Activity.ListItemActivity.ListItemActivity;
import com.yxq.home.Adapter.HomePageAdapter;
import com.yxq.home.Adapter.HomePageAdapter2;
import com.yxq.home.Model.HomePageItem;
import com.yxq.home.Model.HomePageItem2;
import com.yxq.home.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 残 on 2019/11/11.
 */

public class HomePageFragment extends Fragment implements View.OnClickListener {

    //定义view用来设置fragment的layout
    private View view;

    //ListItem 数据
    private List<HomePageItem> HomePageItemList = new ArrayList<>();
    private List<HomePageItem2> HomePageItemList2 = new ArrayList<>();


    //定义各个控件
    private ImageView cIcon;
    private ImageView javaIcon;
    private ImageView phpIcon;
    private ImageView htmlIcon;
    private ImageView pythonIcon;
    private ImageView jsIcon;
    private ImageView linuxIcon;
    private ImageView cmsIcon;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_homepage, container, false);
        initListItemData();  //初始化HomeItemAdapter数据
        initRecyclerView();  //初始化RecyclerView 并发送数据与视图

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
        cIcon = (ImageView) getActivity().findViewById(R.id.c_icon);
        javaIcon = (ImageView) getActivity().findViewById(R.id.java_icon);
        phpIcon = (ImageView) getActivity().findViewById(R.id.php_icon);
        htmlIcon = (ImageView) getActivity().findViewById(R.id.html_icon);
        pythonIcon = (ImageView) getActivity().findViewById(R.id.python_icon);
        jsIcon = (ImageView) getActivity().findViewById(R.id.js_icon);
        linuxIcon = (ImageView) getActivity().findViewById(R.id.linux_icon);
        cmsIcon = (ImageView) getActivity().findViewById(R.id.cms_icon);

        //为控件设置点击事件监听器
        cIcon.setOnClickListener(this);
        javaIcon.setOnClickListener(this);
        phpIcon.setOnClickListener(this);
        htmlIcon.setOnClickListener(this);
        pythonIcon.setOnClickListener(this);
        jsIcon.setOnClickListener(this);
        linuxIcon.setOnClickListener(this);
        cmsIcon.setOnClickListener(this);

    }

    //设置点击事件
    @Override
    public void onClick(View v) {
        //设置跳转的视图
        intent = new Intent(v.getContext(), ListItemActivity.class);
        switch (v.getId()) {
            case R.id.c_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/cc"); //C页面
                intent.putExtra("title","C");
                //跳转
                v.getContext().startActivity(intent);
                break;
            case R.id.java_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/java"); //java页面
                intent.putExtra("title","Java");
                v.getContext().startActivity(intent);
                break;
            case R.id.php_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/php"); //php页面
                intent.putExtra("title","PHP");
                v.getContext().startActivity(intent);
                break;
            case R.id.html_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/html"); //html页面
                intent.putExtra("title","HTML");
                v.getContext().startActivity(intent);
                break;
            case R.id.python_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/python"); //python页面
                intent.putExtra("title","Python");
                v.getContext().startActivity(intent);
                break;
            case R.id.js_icon:
                intent.putExtra("url", "https://cuiqingcai.com/category/technique/javascript"); //js页面
                intent.putExtra("title","Javascript");
                v.getContext().startActivity(intent);
                break;
            case R.id.linux_icon:
                intent.putExtra("url", "https://www.runoob.com/linux/linux-tutorial.html"); //linux页面
                intent.putExtra("title","Linux");
                v.getContext().startActivity(intent);
                break;
            case R.id.cms_icon:
                intent.putExtra("url", "https://itbyc.com/cms/");   //cms页面
                intent.putExtra("title","CMS");
                v.getContext().startActivity(intent);
                break;
        }
    }
    //初始化RecyclerView
    private void initRecyclerView() {
        //获取recyclerView
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.homepage_item_recycleView);
        final RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.homepage_item_recycleView2);
        //实例化线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this.getContext());
        //设置listItemAdapter适配器
        HomePageAdapter homePageAdapter = new HomePageAdapter(HomePageItemList);
        HomePageAdapter2 homePageAdapter2 = new HomePageAdapter2(HomePageItemList2);
        //设置布局
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        //设置适配器
        recyclerView.setAdapter(homePageAdapter);
        recyclerView2.setAdapter(homePageAdapter2);


    }

    //初始化listItemAdapter数据
    private void initListItemData() {
        if (HomePageItemList.size() == 0) {
            HomePageItem listItem1 = new HomePageItem("Python2爬虫学习系列教程","♡31370喜欢", "https://cuiqingcai.com/1052.html");
            HomePageItemList.add(listItem1);
            HomePageItem listItem2 = new HomePageItem("七分钟全面了解位运算","♡5765喜欢" , "https://cuiqingcai.com/6841.html");
            HomePageItemList.add(listItem2);
            HomePageItem listItem3 = new HomePageItem("Python3爬虫视频学习教程","♡3232喜欢", "https://cuiqingcai.com/4320.html");
            HomePageItemList.add(listItem3);
            HomePageItem listItem4 = new HomePageItem("Python爬虫入门一之综述","♡2616喜欢" , "https://cuiqingcai.com/927.html");
            HomePageItemList.add(listItem4);
            HomePageItem listItem5 = new HomePageItem("Python入门Urllib库的使用", "♡2540喜欢", "https://cuiqingcai.com/947.html");
            HomePageItemList.add(listItem5);
        }
        if (HomePageItemList2.size() == 0){
            HomePageItem2 listItem1 =  new HomePageItem2(R.drawable.article1,"Python2爬虫学习系列教程","2019-11-23","https://cuiqingcai.com/1052.html");
            HomePageItemList2.add(listItem1);
            HomePageItem2 listItem2 =  new HomePageItem2(R.drawable.article2,"七分钟全面了解位运算","2019-11-23","https://cuiqingcai.com/6841.html");
            HomePageItemList2.add(listItem2);
            HomePageItem2 listItem3 =  new HomePageItem2(R.drawable.article3,"Python3爬虫视频学习教程","2019-11-23","https://cuiqingcai.com/4320.html");
            HomePageItemList2.add(listItem3);
            HomePageItem2 listItem4 =  new HomePageItem2(R.drawable.article4,"Python爬虫入门一之综述","2019-11-23","https://cuiqingcai.com/927.html");
            HomePageItemList2.add(listItem4);
            HomePageItem2 listItem5 =  new HomePageItem2(R.drawable.article5,"Python入门Urllib库的使用","2019-11-23","https://cuiqingcai.com/947.html");
            HomePageItemList2.add(listItem5);
        }

    }
}
