package com.wh.entity;

import java.io.Serializable;

/**
 * (Price)实体类
 *
 * @author makejava
 * @since 2021-04-27 15:13:54
 */
public class Price implements Serializable {
    private static final long serialVersionUID = 577895413569182575L;

    private Integer pId;

    private Double price;

    private String imgUrl;

    private String des;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Price{" +
                "pId=" + pId +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
