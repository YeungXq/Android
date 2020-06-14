package com.yxq.home.Model;

/**
 * Created by 残 on 2019/11/24.
 */

public class HomePageItem {
    private String itemName; //排行名字
    private String itemLike; //排行喜欢
    private String intendUrl; //Url

    public HomePageItem() {
    }

    public HomePageItem(String itemName, String itemLike, String intendUrl) {
        this.itemName = itemName;
        this.itemLike = itemLike;
        this.intendUrl = intendUrl;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemLike() {
        return itemLike;
    }

    public void setItemLike(String itemLike) {
        this.itemLike = itemLike;
    }

    public String getIntendUrl() {
        return intendUrl;
    }

    public void setIntendUrl(String intendUrl) {
        this.intendUrl = intendUrl;
    }

    @Override
    public String toString() {
        return "HomePageItem{" +
                "itemName='" + itemName + '\'' +
                ", itemLike='" + itemLike + '\'' +
                ", intendUrl='" + intendUrl + '\'' +
                '}';
    }
}
