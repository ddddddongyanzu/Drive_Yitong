package com.wh.vo;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class DeleteStudentvo {
    @ApiParam("用户id")
    private Integer uId;
    @ApiParam("学员id")
    private Integer sId;
}
