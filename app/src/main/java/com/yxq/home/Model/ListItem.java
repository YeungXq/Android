package com.yxq.home.Model;

/**
 * Created by 残 on 2019/11/23.
 */

public class ListItem {
    private int listItemImg; //列表文章图片
    private String listItemTitle; //列表文章标题
    private String listItemTime; //列表文章发布时间
    private String intendUrl; //列表文章Url

    public ListItem() {
    }

    public ListItem(int listItemImg, String listItemTitle, String listItemTime, String intendUrl) {
        this.listItemImg = listItemImg;
        this.listItemTitle = listItemTitle;
        this.listItemTime = listItemTime;
        this.intendUrl = intendUrl;
    }

    public int getListItemImg() {
        return listItemImg;
    }

    public void setListItemImg(int listItemImg) {
        this.listItemImg = listItemImg;
    }

    public String getListItemTitle() {
        return listItemTitle;
    }

    public void setListItemTitle(String listItemTitle) {
        this.listItemTitle = listItemTitle;
    }

    public String getListItemTime() {
        return listItemTime;
    }

    public void setListItemTime(String listItemTime) {
        this.listItemTime = listItemTime;
    }

    public String getIntendUrl() {
        return intendUrl;
    }

    public void setIntendUrl(String intendUrl) {
        this.intendUrl = intendUrl;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "listItemImg=" + listItemImg +
                ", listItemTitle='" + listItemTitle + '\'' +
                ", listItemTime='" + listItemTime + '\'' +
                ", intendUrl='" + intendUrl + '\'' +
                '}';
    }
}
