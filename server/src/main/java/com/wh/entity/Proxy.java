package com.wh.entity;

import java.io.Serializable;

/**
 * (Proxy)实体类
 *
 * @author makejava
 * @since 2021-04-27 15:14:13
 */
public class Proxy implements Serializable {
    private static final long serialVersionUID = -46299914566592834L;

    private Integer pId;
    /**
     * 代理人的用户id
     */
    private Integer uId;

    private String openid;

    private String name;

    private String phoneNumber;

    private String des;

    private Double basePrice;

    private String address;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Proxy{" +
                "pId=" + pId +
                ", uId=" + uId +
                ", openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", des='" + des + '\'' +
                ", basePrice=" + basePrice +
                ", address='" + address + '\'' +
                '}';
    }
}
