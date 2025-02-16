package com.wh.entity;

import java.io.Serializable;

/**
 * (Banner)实体类
 *
 * @author makejava
 * @since 2021-04-27 15:13:47
 */
public class Banner implements Serializable {
    private static final long serialVersionUID = -75149508582898057L;

    private Integer bId;
    /**
     * 广告图片
     */
    private String bannerUrl;
    /**
     * 标记是否显示
     */
    private Integer isShow;


    public Integer getBId() {
        return bId;
    }

    public void setBId(Integer bId) {
        this.bId = bId;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

}
