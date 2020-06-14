package com.yxq.home.Activity.UserItemActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yxq.home.Activity.BaseActivity;
import com.yxq.home.Activity.LoginActivity;
import com.yxq.home.DataBase.MyDatabaseHelper;
import com.yxq.home.R;

import static com.yxq.home.R.layout.queryinfo_update;

/**
 * Created by 残 on 2019/11/19.
 */

/**
 * 个人信息
 */
public class QueryInfo extends BaseActivity {

    //数据库
    private MyDatabaseHelper dbHelper;

    private TextView userIdView;

    private TextView passWordView;
    private ImageView imageView;

    private Button updateBtn;
    Intent intent;

    private String id;
    private String passWord;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queryinfo);
        //new一个数据库类的对象，并且指定数据库的名称，版本号
        dbHelper = new MyDatabaseHelper(this, "UserStore", null, 2);
        //获取一个数据库的实例 。数据库存在，打开数据库；数据库不存在，创建数据库并打开
        dbHelper.getWritableDatabase();


        //初始化事件
        initEvent();

        //获取 父视图发来的数据
        getParentData();

        //设置事件中的数据
        setEventData();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {
                    imageView.setImageResource(R.drawable.show);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    Cursor cursor = null;
                    cursor = db.rawQuery("select * from user where userId = ?", new String[]{id});
                    if (cursor != null && cursor.moveToFirst()) {
                        passWord = cursor.getString(cursor.getColumnIndex("password"));
                    }
                    cursor.close();
                    passWordView.setText(passWord);
                    flag = true;
                } else {
                    imageView.setImageResource(R.drawable.hide);
                    passWordView.setText("*******");
                    flag = false;
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddDialog();
            }
        });

    }

    protected void showAddDialog() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(queryinfo_update, null);
        final EditText oldPassword = (EditText) textEntryView.findViewById(R.id.old_Password);
        final EditText newPassword = (EditText) textEntryView.findViewById(R.id.update_Password);
        final EditText confirmPassword = (EditText) textEntryView.findViewById(R.id.confirm_Password);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改密码");
        builder.setView(textEntryView);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = null;
                cursor = db.rawQuery("select * from user where userId = ?", new String[]{id});
                if (cursor != null && cursor.moveToFirst()) {
                    String checkPassword = cursor.getString(cursor.getColumnIndex("password"));
                    if (oldPassword.getText().toString().equals(checkPassword)) {
                        if (newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                            ContentValues values = new ContentValues();
                            //put的内容是要修改的字段
                            values.put("password", newPassword.getText().toString());
                            //方法一
                            //第一个参数：表名;第二个参数：values;第三个参数：where条件;第四格参数：where条件对应字段值
                            db.update("user", values, "userId = ?", new String[]{id});
                            Toast.makeText(QueryInfo.this, "修改成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(QueryInfo.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(QueryInfo.this, "原密码错误！", Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.show();// 显示对话框
    }

    private void initEvent() {
        userIdView = (TextView) findViewById(R.id.allInfo_UserId);
        passWordView = (TextView) findViewById(R.id.allInfo_Password);
        imageView = (ImageView) findViewById(R.id.allInfo_Img);
        updateBtn = (Button) findViewById(R.id.update_btn);
    }

    public void getParentData() {
        intent = getIntent();
        id = intent.getStringExtra("id");


    }

    public void setEventData() {
        userIdView.setText(id);
    }
}
