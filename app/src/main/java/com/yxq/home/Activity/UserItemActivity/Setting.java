package com.yxq.home.Activity.UserItemActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yxq.home.Activity.BaseActivity;
import com.yxq.home.R;

/**
 * Created by 残 on 2019/11/19.
 */

/**
 * 设置
 */
public class Setting extends BaseActivity implements View.OnClickListener {
    private ImageView setting1;
    private ImageView setting2;
    private ImageView setting3;
    private boolean flag = true;//定义一个标识符，用来判断是否被选择
    private boolean flag2 = true;
    private boolean flag3 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_setting);
        //初始化控件
        initView();
        setting1.setOnClickListener(this);
        setting2.setOnClickListener(this);
        setting3.setOnClickListener(this);
    }

    private void initView() {
        setting1 = (ImageView) findViewById(R.id.setting1_icon);
        setting2 = (ImageView) findViewById(R.id.setting2_icon);
        setting3 = (ImageView) findViewById(R.id.setting3_icon);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting1_icon:
                if (flag) {
                    setting1.setImageResource(R.drawable.setting_item_selected);
                    flag = false;
                } else {
                    setting1.setImageResource(R.drawable.setting_item);
                    flag = true;
                }
                break;

            case R.id.setting2_icon:
                if (flag2) {
                    setting2.setImageResource(R.drawable.setting_item_selected);
                    flag2 = false;
                } else {
                    setting2.setImageResource(R.drawable.setting_item);
                    flag2 = true;
                }
                break;
            case R.id.setting3_icon:
                if (flag3) {
                    setting3.setImageResource(R.drawable.setting_item_selected);
                    flag3 = false;
                } else {
                    setting3.setImageResource(R.drawable.setting_item);
                    flag3 = true;
                }
                break;

        }
    }
}
