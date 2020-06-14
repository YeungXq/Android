package com.yxq.home.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yxq.home.DataBase.MyDatabaseHelper;
import com.yxq.home.R;


public class LoginActivity extends BaseActivity {
    //数据库
    private MyDatabaseHelper dbHelper;

    EditText userId;  // 账号框
    EditText userPassword; //密码
    TextView registerBtn; //注册账号；
    Button loginBtn; //登录按钮

    private CheckBox checkBox;

    private SharedPreferences share;

    private SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //初始化事件
        initEvent();

        //new一个数据库类的对象，并且指定数据库的名称，版本号
        dbHelper = new MyDatabaseHelper(this, "UserStore", null, 2);
        //获取一个数据库的实例 。数据库存在，打开数据库；数据库不存在，创建数据库并打开
        dbHelper.getWritableDatabase();

        //设置注册账号的TextView为可点击
        registerBtn.setClickable(true);

        //设置点击注册  跳转注册界面
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        share = getSharedPreferences("data",MODE_PRIVATE);
        boolean isRemember = share.getBoolean("isRemember",false);
        //判断是否记住密码，若记住密码则自动补充信息
        if (isRemember){
            String account = share.getString("name","");
            String password = share.getString("password","");
            userId.setText(account);
            userPassword.setText(password);
            checkBox.setChecked(true);
        }

        //登录操作
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String id = userId.getText().toString();
                String password = userPassword.getText().toString();
                Cursor cursor = null;
                cursor = db.rawQuery("select * from user where userId = ?",new String[]{id});
                if (cursor != null  && cursor.moveToFirst()){
                    String checkPassword = cursor.getString(cursor. getColumnIndex("password"));
                    if (checkPassword.equals(password)){
                        edit = share.edit();
                        if (checkBox.isChecked()){
                            edit.putString("name",id).commit();
                            edit.putString("password",password).commit();
                            edit.putBoolean("isRemember",true);
                            edit.commit();
                        }else {
                            edit.clear();
                        }
                        Toast.makeText(LoginActivity.this, "登陆成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("id", id);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "id or password is invalid",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "用户不存在",
                            Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });


    }

    //初始化事件
    private void initEvent() {
        userId = (EditText) findViewById(R.id.loginUserId);
        userPassword = (EditText) findViewById(R.id.loginUserPassword);
        registerBtn = (TextView) findViewById(R.id.registerIntentBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
    }


}
