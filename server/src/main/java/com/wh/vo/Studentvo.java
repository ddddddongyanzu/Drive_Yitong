package com.wh.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data

public class Studentvo {
    private String name;
    private String address;
    private String phoneNumber;
    @ApiParam("用户id")
    private int uId;
    @ApiParam("学员id")
    private int sId;
}
