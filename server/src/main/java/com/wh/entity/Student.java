package com.wh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2021-04-27 20:30:31
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -29895804042403358L;

    private Integer sId;
    /**
     * 用户id
     */
    private Integer uId;
    /**
     * 该学生的代理人是谁
     */
    private Integer pId;
    /**
     * 微信id
     */
    private String openid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 电话
     */
    private String phoneNumber;
    /**
     * 身份证号码
     */
    private String cardNumber;
    /**
     * 所选车型
     */
    private Integer carType;
    /**
     * 所在地
     */
    private String address;
    /**
     * 报名时间
     */
    private Date registerTime;

    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 缴费金额
     */
    private Double payAmount;


    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", uId=" + uId +
                ", pId=" + pId +
                ", openid='" + openid + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", carType=" + carType +
                ", address='" + address + '\'' +
                ", registerTime=" + registerTime +
                ", updateTime=" + updateTime +
                ", payAmount=" + payAmount +
                '}';
    }
}
