package com.yxq.home.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yxq.home.Controller.ActivityController;
/*
自定义的活动基类  主要用于控制活动
 */


public class BaseActivity extends AppCompatActivity {


    /*
    创建活动  活动控制器添加当前活动
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    /*
    销毁活动  活动控制器删除当前活动
    */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }




}
