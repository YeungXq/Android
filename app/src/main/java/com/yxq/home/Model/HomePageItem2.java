package com.yxq.home.Model;



/**
 * Created by 残 on 2019/11/24.
 */

public class HomePageItem2{
    private int homePageImg;
    private String homePageTitle;
    private String homePageTime;
    private String intendUrl;

    public HomePageItem2() {
    }

    public HomePageItem2(int homePageImg, String homePageTitle, String homePageTime, String intendUrl) {
        this.homePageImg = homePageImg;
        this.homePageTitle = homePageTitle;
        this.homePageTime = homePageTime;
        this.intendUrl = intendUrl;
    }

    public int getHomePageImg() {
        return homePageImg;
    }

    public void setHomePageImg(int homePageImg) {
        this.homePageImg = homePageImg;
    }

    public String getHomePageTitle() {
        return homePageTitle;
    }

    public void setHomePageTitle(String homePageTitle) {
        this.homePageTitle = homePageTitle;
    }

    public String getHomePageTime() {
        return homePageTime;
    }

    public void setHomePageTime(String homePageTime) {
        this.homePageTime = homePageTime;
    }

    public String getIntendUrl() {
        return intendUrl;
    }

    public void setIntendUrl(String intendUrl) {
        this.intendUrl = intendUrl;
    }

    @Override
    public String toString() {
        return "HomePageItem2{" +
                "homePageImg=" + homePageImg +
                ", homePageTitle='" + homePageTitle + '\'' +
                ", homePageTime='" + homePageTime + '\'' +
                ", intendUrl='" + intendUrl + '\'' +
                '}';
    }
}
