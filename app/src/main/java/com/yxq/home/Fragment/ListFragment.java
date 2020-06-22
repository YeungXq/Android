package com.yxq.home.Fragment;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.yxq.home.Adapter.ListItemAdapter;
import com.yxq.home.DataBase.MyDatabaseHelper;
import com.yxq.home.Model.ListItem;
import com.yxq.home.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yxq.home.R.layout.article_add;
import static com.yxq.home.R.layout.dynamic_add;

/**
 * Created by 残 on 2019/11/23.
 */

public class ListFragment extends Fragment {
    //ListItem 数据
    private List<ListItem> listItemList = new ArrayList<>();

    //数据库
    private MyDatabaseHelper dbHelper;
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
    private FloatingActionButton article_add_btn;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Activity activity;

    //定义view用来设置fragment的layout
    private View view;

    boolean flag = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //获取view用来设置fragment的layout
        view = inflater.inflate(R.layout.fragment_list, container, false);
        //new一个数据库类的对象，并且指定数据库的名称，版本号
        activity = getActivity();
        dbHelper = new MyDatabaseHelper(activity, "UserStore", null, 3);
        initView();
        initRecyclerView();  //初始化RecyclerView 并发送数据与视图
        if (flag) {
            initListItemData();  //初始化ListItemAdapter数据
            flag = false;
        }
        //下拉刷新功能
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listItemList.clear();
                initRecyclerView();
                initListItemData();
                //数据重新加载完成后，设置现在不在刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void initView() {
        article_add_btn = (FloatingActionButton) view.findViewById(R.id.article_add);
        article_add_btn.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.article_refresh_layout);


    }


    protected void showAddDialog() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(article_add, null);
        final EditText title = (EditText) textEntryView.findViewById(R.id.add_title);
        final EditText url = (EditText) textEntryView.findViewById(R.id.add_url);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加链接");
        builder.setView(textEntryView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //将信息保存到数据库
                if (!title.getText().toString().equals("") && !url.getText().toString().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("title", title.getText().toString());
                    values.put("time", sdfDate.format(new Date()));
                    values.put("url", url.getText().toString());
                    db.insert("article", null, values);
                } else {
                    Toast.makeText(activity, "内容不能为空！", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.show();// 显示对话框
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
        Cursor cursor = null;
        String time = "";
        String title = "";
        String url = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from article", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                title = cursor.getString(cursor.getColumnIndex("title"));
                time = cursor.getString(cursor.getColumnIndex("time"));
                url = cursor.getString(cursor.getColumnIndex("url"));
                listItemList.add(new ListItem(R.drawable.article,title,time,"https://"+url));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
    }
}
