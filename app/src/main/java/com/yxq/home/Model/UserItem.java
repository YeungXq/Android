package com.yxq.home.Model;

/**
 * 用户界面布局 实体类.
 */

public class UserItem {
    private int userItemIcon;
    private String userItemName;
    private Class intendUrl;

    public UserItem() {
    }

    public UserItem(int userItemIcon, String userItemName, Class intendUrl) {
        this.userItemIcon = userItemIcon;
        this.userItemName = userItemName;
        this.intendUrl = intendUrl;
    }

    public Class getIntendUrl() {
        return intendUrl;
    }

    public void setIntendUrl(Class intendUrl) {
        this.intendUrl = intendUrl;
    }

    public int getUserItemIcon() {
        return userItemIcon;
    }

    public String getUserItemName() {
        return userItemName;
    }

    public void setUserItemIcon(int userItemIcon) {
        this.userItemIcon = userItemIcon;
    }

    public void setUserItemName(String userItemName) {
        this.userItemName = userItemName;
    }


    @Override
    public String toString() {
        return "UserItem{" +
                "userItemIcon=" + userItemIcon +
                ", userItemName='" + userItemName + '\'' +
                ", intendUrl=" + intendUrl +
                '}';
    }
}
