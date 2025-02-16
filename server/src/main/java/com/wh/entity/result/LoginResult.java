package com.wh.entity.result;

import com.wh.entity.User;

/**
 * @author : WH
 * @date : 2021/4/26 11:17
 */
public class LoginResult {
    private User user;
    private WeiXinApiLoginResult weiXinApiLoginResult;

    public LoginResult() {
    }

    public LoginResult(User user, WeiXinApiLoginResult weiXinApiLoginResult) {
        this.user = user;
        this.weiXinApiLoginResult = weiXinApiLoginResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WeiXinApiLoginResult getWeiXinApiLoginResult() {
        return weiXinApiLoginResult;
    }

    public void setWeiXinApiLoginResult(WeiXinApiLoginResult weiXinApiLoginResult) {
        this.weiXinApiLoginResult = weiXinApiLoginResult;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "user=" + user +
                ", weiXinApiLoginResult=" + weiXinApiLoginResult +
                '}';
    }
}
