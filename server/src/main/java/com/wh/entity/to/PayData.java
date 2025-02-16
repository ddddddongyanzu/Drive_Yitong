package com.wh.entity.to;

/**
 * @author : WH
 * @date : 2021/5/12 23:52
 */
public class PayData {
    private String timeStamp;
    private String nonceStr;
    private String paySign;
    private String prepay_id;
    private String out_trade_no;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    @Override
    public String toString() {
        return "PayData{" +
                "timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", paySign='" + paySign + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                '}';
    }
}
