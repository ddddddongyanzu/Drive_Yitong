package com.wh.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class UpdateAdminvo {
    @ApiParam("原密码")
    private String oldPwd;

    @ApiParam("新密码")
    private String newPwd;

    @ApiParam("管理员账号id")
    private Integer aId;

    @ApiParam("管理员用户名")
    private String userName;
}
