package com.wh.entity.result;

import com.wh.entity.Admin;

/**
 * @author : WH
 * @date : 2021/5/9 12:04
 */
public class BackLoginResult {
    private Admin admin;

    private int isSuccess;

    @Override
    public String toString() {
        return "BackLoginResult{" +
                "admin=" + admin +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public int getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(int isSuccess) {
        this.isSuccess = isSuccess;
    }
}
