package com.wh.entity.to;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author : WH
 * @date : 2021/5/12 22:59
 */
public class OrderData {

    private String mchid;

    private String out_trade_no;

    private String appid;

    private String description;

    private String notify_url;

    private Amount amount;

    private payer payer;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public com.wh.entity.to.payer getPayer() {
        return payer;
    }

    public void setPayer(com.wh.entity.to.payer payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return "OrderData{" +
                "mchid='" + mchid + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", appid='" + appid + '\'' +
                ", description='" + description + '\'' +
                ", notify_url='" + notify_url + '\'' +
                ", amount=" + amount +
                ", payer=" + payer +
                '}';
    }
}
