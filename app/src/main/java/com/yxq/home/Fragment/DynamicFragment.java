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

import com.yxq.home.Activity.UserItemActivity.QueryInfo;
import com.yxq.home.Adapter.DynamicAdapter;
import com.yxq.home.DataBase.MyDatabaseHelper;
import com.yxq.home.Model.DynamicItem;
import com.yxq.home.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yxq.home.R.layout.dynamic_add;


public class DynamicFragment extends Fragment {
    //数据库
    private MyDatabaseHelper dbHelper;


    //定义view用来设置fragment的layout
    private View view;
    private SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private FloatingActionButton btn_add;
    private SwipeRefreshLayout swipeRefreshLayout;

    private Activity activity;

    //ListItem 数据
    private List<DynamicItem> dynamicList = new ArrayList<>();
    boolean flag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);

        activity = getActivity();
        //new一个数据库类的对象，并且指定数据库的名称，版本号
        dbHelper = new MyDatabaseHelper(activity, "UserStore", null, 2);
        initView();
        initRecyclerView();  //初始化RecyclerView 并发送数据与视图
        if (flag) {
            initMemorandumData();
            flag = false;
        }
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dynamicList.clear();
                initRecyclerView();
                initMemorandumData();
                //数据重新加载完成后，提示数据发生改变，并且设置现在不在刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void initView() {
        btn_add = (FloatingActionButton) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);


    }

    protected void showAddDialog() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(dynamic_add, null);
        final EditText editText = (EditText) textEntryView.findViewById(R.id.add_content);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("添加动态");
        builder.setView(textEntryView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (!editText.getText().toString().equals("")) {
                    ContentValues values = new ContentValues();
                    values.put("time", sdfDate.format(new Date()));
                    values.put("message", editText.getText().toString());
                    db.insert("message", null, values);
                } else {
                    Toast.makeText(activity, "内容不能为空！", Toast.LENGTH_SHORT).show();
                }
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
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dynamic_recyclerView);
        //实例化线性布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        //设置listItemAdapter适配器
        DynamicAdapter dynamicAdapter = new DynamicAdapter(dynamicList);
        //设置布局
        recyclerView.setLayoutManager(layoutManager);
        //设置适配器
        recyclerView.setAdapter(dynamicAdapter);

    }

    //初始化MemorandumItemAdapter数据
    private void initMemorandumData() {
        Cursor cursor = null;
        String time = "";
        String message = "";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from message order by time desc", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                time = cursor.getString(cursor.getColumnIndex("time"));
                message = cursor.getString(cursor.getColumnIndex("message"));
                Date date = null;
                try {
                    date = sdfDate.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                dynamicList.add(new DynamicItem(date, message));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

}