package com.wh.entity;

import java.io.Serializable;

/**
 * (Admin)实体类
 *
 * @author makejava
 * @since 2021-04-27 15:13:11
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 823941520737719396L;

    private Integer aId;

    private String userName;

    private String password;

    private String nickName;
    /**
     * 头像
     */
    private String avatarUrl;


    public Integer getAId() {
        return aId;
    }

    public void setAId(Integer aId) {
        this.aId = aId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
