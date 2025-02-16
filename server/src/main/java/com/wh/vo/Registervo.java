package com.wh.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Registervo {
   

    @ApiParam("用户id")
    private Integer uId;

    @ApiParam("车型")
    private Integer carType;

    @ApiParam("支付金额")
    private double payAmount;


    private Integer proxy_uid;
    @ApiParam("学员")
    private String student;
}
