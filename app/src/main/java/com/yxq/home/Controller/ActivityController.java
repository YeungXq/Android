package com.yxq.home.Controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动控制器
 */
public class ActivityController {

    //所有活动
    public static List<Activity> activities = new ArrayList<>();

    //添加一个活动
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    //删除一个活动
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    //删除所有活动
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
