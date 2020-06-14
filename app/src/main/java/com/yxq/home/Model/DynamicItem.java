package com.yxq.home.Model;

import java.util.Date;

/**
 * Created by Yeung on 2020/5/26.
 */

public class DynamicItem {
    private Date createTime;
    private String message;

    public DynamicItem() {
    }

    public DynamicItem(Date createTime, String message) {
        this.createTime = createTime;
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DynamicItem{" +
                "createTime=" + createTime +
                ", message='" + message + '\'' +
                '}';
    }
}
