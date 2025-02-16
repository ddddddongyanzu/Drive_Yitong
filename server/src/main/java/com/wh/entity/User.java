package com.wh.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-04-27 15:14:46
 */
public class User implements Serializable {
    private static final long serialVersionUID = -27274338151755963L;

    private Integer uId;

    private String openid;


    private String nickName;

    private String avatarUrl;

    private Integer gender;

    private String province;

    private String city;
    /**
     * 0：普通用户 1：代理人
     */
    private Integer role;

    private Integer isRegister;
    private Integer isStudent;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Integer isRegister) {
        this.isRegister = isRegister;
    }
    public Integer getIsStudent(){return isStudent;}
    public void setIsStudent(Integer isStudent){this.isStudent = isStudent;}

    @Override
    public String toString() {
        return "User{" +
                "uId=" + uId +
                ", openid='" + openid + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", gender=" + gender +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", role=" + role +
                ", isRegister=" + isRegister +
                ", isStudent="+ isStudent +
                '}';
    }
}
