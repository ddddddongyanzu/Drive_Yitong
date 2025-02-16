package com.wh.controller;

import com.wh.service.PortalWechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author panda
 * @date 2021/4/27 14:06
 * <p>
 * description 回调接口
 */
@RestController
@RequestMapping("/pay")
public class PortalWechatController {

    @Autowired
    private PortalWechatService portalWechatService;

    @PostMapping("/wx/callback")
    @ResponseBody
    public String wxCallback(HttpServletRequest request){
        return portalWechatService.callBack(request);
    }
}

