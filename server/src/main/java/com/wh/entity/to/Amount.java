package com.wh.entity.to;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author : WH
 * @date : 2021/5/11 18:31
 */
public class Amount {
    private int total;

    private String currency;

    public Amount() {
    }

    public Amount(int total, String currency) {
        this.total = total;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "amount{" +
                "total=" + total +
                ", currency=" + currency +
                '}';
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
