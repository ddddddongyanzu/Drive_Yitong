package com.wh.entity.to;

/**
 * @author : WH
 * @date : 2021/5/11 18:32
 */
public class payer {
    private String openid;


    public payer(String openid) {
        this.openid = openid;
    }

    public payer() {
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "payer{" +
                "openid='" + openid + '\'' +
                '}';
    }
}
