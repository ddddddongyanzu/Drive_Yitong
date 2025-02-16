package com.wh.entity.result;

/**
 * @author : WH
 * @date : 2021/5/12 23:44
 */
public class WXOrderResult {
    private String prepay_id;

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    @Override
    public String toString() {
        return "WXOrderResult{" +
                "prepay_id='" + prepay_id + '\'' +
                '}';
    }
}
