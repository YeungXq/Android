package com.yxq.home.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.yxq.home.Activity.UserItemActivity.AboutUs;
import com.yxq.home.Activity.UserItemActivity.QueryInfo;
import com.yxq.home.Activity.UserItemActivity.Quit;
import com.yxq.home.Activity.UserItemActivity.Setting;
import com.yxq.home.Adapter.UserItemAdapter;
import com.yxq.home.Activity.MainActivity;
import com.yxq.home.Model.UserItem;
import com.yxq.home.R;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {


    //UserItem 数据
    private List<UserItem> userItemList = new ArrayList<>();

    //定义view用来设置fragment的layout
    private View view;


    //接收MainActicity的视图
    private Intent intent;

    //获取登录的id
    private String userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取view用来设置fragment的layout
        view = inflater.inflate(R.layout.fragment_user, container, false);
        getActivityData();   //获取LoginActivity传给MainActivity的数据  并获取视图
        initUserItemData();  //初始化userItemAdapter数据
        initRecyclerView();  //初始化RecyclerView 并发送数据与视图
        initEvent();  //初始化事件 并赋值
        return view;
    }


    //初始化RecyclerView
    private void initRecyclerView() {
        //获取recyclerView
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.user_item_recycleView);
        //实例化线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        //设置userItemAdapter适配器
        UserItemAdapter userItemAdapter = new UserItemAdapter(userItemList, intent);
        //设置布局
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        recyclerView.setAdapter(userItemAdapter);


    }


    //初始化userItemAdapter数据
    private void initUserItemData() {
        if (userItemList.size() == 0) {
            UserItem queryAllInfo = new UserItem(R.drawable.information, "查看信息", QueryInfo.class);
            userItemList.add(queryAllInfo);

            UserItem aboutUs = new UserItem(R.drawable.aboutus, "关于我们", AboutUs.class);
            userItemList.add(aboutUs);

            UserItem modifyPassword = new UserItem(R.drawable.setting, "设置", Setting.class);
            userItemList.add(modifyPassword);

            UserItem logout = new UserItem(R.drawable.quit, "退出", Quit.class);
            userItemList.add(logout);
        }

    }

    //获取LoginActivity传给MainActivity的数据
    public void getActivityData() {
        MainActivity mainActivity = (MainActivity) getActivity();
        intent = mainActivity.getIntent();
        userId = intent.getStringExtra("id");
    }

    //初始化事件
    private void initEvent() {
        //获取名字的View
        TextView idView = (TextView) view.findViewById(R.id.user_id_View);
        //设置文本
        idView.setText(userId);
        //设置可点击
        idView.setClickable(true);
    }
}
