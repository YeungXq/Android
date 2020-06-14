package com.yxq.home.Activity.UserItemActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.yxq.home.Activity.BaseActivity;
import com.yxq.home.Controller.ActivityController;
import com.yxq.home.Activity.LoginActivity;
import com.yxq.home.R;

/**
 * Created by 残 on 2019/11/19.
 */

/**
 * 退出登录
 */
public class Quit extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quit);
        //清除所有活动
        ActivityController.finishAll();
        //提示退出成功
        Toast.makeText(Quit.this,"退出成功！",Toast.LENGTH_LONG).show();
        //跳转登录页
        Intent intent = new Intent(Quit.this,LoginActivity.class);
        startActivity(intent);
    }
}
