package com.yxq.home.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yxq.home.DataBase.MyDatabaseHelper;
import com.yxq.home.R;


public class RegisterActivity extends BaseActivity {
    //数据库
    private MyDatabaseHelper dbHelper;

    TextView loginBtn; //登录页面
    TextView registerBtn; //注册

    private EditText registerUserId; //注册页面用户ID框
    private EditText registerUserPassword; //注册页面用户密码框
    private EditText registerConfirmUserPassword; //注册页面确认密码框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化事件
        initEvent();

        //new一个数据库类的对象，并且指定数据库的名称，版本号
        dbHelper = new MyDatabaseHelper(this, "UserStore", null, 2);

        //设置进入登录页面的TextView为可点击
        loginBtn.setClickable(true);

        //设置点击登录  跳转登录界面
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        //注册
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //获取注册信息
                String registerId = registerUserId.getText().toString();
                String registerPassword = registerUserPassword.getText().toString();
                String confirmPassword = registerConfirmUserPassword.getText().toString();
                Cursor cursor = null;
                cursor = db.rawQuery("select * from user where userId = ?", new String[]{registerId});
                if (cursor != null && cursor.moveToFirst()) {
                    Toast.makeText(RegisterActivity.this, "用户已存在！", Toast.LENGTH_SHORT).show();
                } else if (!registerId.equals("") && !registerPassword.equals("") && confirmPassword.equals(registerPassword)) {
                    ContentValues values = new ContentValues();
                    // 开始组装第二条数据
                    values.put("userId", registerId);
                    values.put("password", registerPassword);
                    db.insert("user", null, values);
                    Log.d("RegisterActivity", "注册成功");
                    Toast.makeText(RegisterActivity.this, "注册成功！跳转登录页面", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegisterActivity.this, "有空值或两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
    }

    //初始化事件
    private void initEvent() {
        loginBtn = (TextView) findViewById(R.id.loginIntentBtn);
        registerUserId = (EditText) findViewById(R.id.registerUserId);
        registerUserPassword = (EditText) findViewById(R.id.registerUserPassword);
        registerConfirmUserPassword = (EditText) findViewById(R.id.registerConfirmUserPassword);
        registerBtn = (TextView) findViewById(R.id.registerBtn);
    }


}
