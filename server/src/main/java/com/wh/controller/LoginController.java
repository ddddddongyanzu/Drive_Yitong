package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.github.kevinsawicki.http.HttpRequest;
import com.wh.entity.Admin;
import com.wh.entity.User;
import com.wh.entity.result.BackLoginResult;
import com.wh.entity.result.LoginResult;
import com.wh.entity.result.WeiXinApiLoginResult;
import com.wh.service.AdminService;
import com.wh.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : WH
 * @date : 2021/4/24 14:23
 */
@RestController
public class LoginController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;


//    小程序一键登录
    @RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
    public LoginResult login(String code, String loginUserInfo){
        System.out.println(code);
        System.out.println(loginUserInfo);
        //接受过来的用户信息json字符串转换为java user对象
        User loginUser = JSON.parseObject(loginUserInfo, User.class);
        //请求参数
        Map<String, String> map = new HashMap<>();
        map.put("appid", appid);
        map.put("secret", secret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //请求地址
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HttpRequest httpRequest = HttpRequest.get(url, map, Boolean.TRUE);
        String result = httpRequest.body();
        System.out.println(result);
        WeiXinApiLoginResult weiXinApiLoginResult = new WeiXinApiLoginResult();
        weiXinApiLoginResult = JSON.parseObject(result, WeiXinApiLoginResult.class);
        System.out.println(weiXinApiLoginResult);

        //将微信号复制给loginUser对象
        loginUser.setOpenid((weiXinApiLoginResult.getOpenid()));
        //游客用户的role为1 代理为3
        //查询该微信号有没有注册过
        User user = userService.queryByOpenid((weiXinApiLoginResult.getOpenid()));
        if (user == null) {
            //没有注册过将用户发过来的信息写入数据库
            System.out.println("该用户第一次登陆，准备创建");
            loginUser.setRole(0);  //将其权限设置为0
            loginUser.setIsRegister(0);
            User insert = userService.insert(loginUser);
            System.out.println("该用户创建成功");
        }else {
            loginUser.setRole(user.getRole());
//            老用户每次登录更新一下数据库，将客户最新信息存在数据库里
            loginUser.setuId(user.getuId());
            loginUser.setIsRegister(user.getIsRegister());
            System.out.println("更新的的用户数据为");
            System.out.println(loginUser);
            User insert = userService.update(loginUser);
            System.out.println("老用户登陆,已更新最新数据");
        }
//        将loginResult返返回
        return new LoginResult(loginUser,weiXinApiLoginResult);
    }

//    后台管理系统登录
    @RequestMapping(value = "/backLogin", method = {RequestMethod.POST,RequestMethod.GET})
    public BackLoginResult backLogin(@RequestBody @ApiParam("管理员对象")Admin admin){
        System.out.println(admin);
//        this.adminService
        Admin loginAdmin = this.adminService.queryByEntity(admin);
        BackLoginResult backLoginResult = new BackLoginResult();

        if(loginAdmin!=null){
            loginAdmin.setPassword("*********");
            backLoginResult.setAdmin(loginAdmin);
            backLoginResult.setIsSuccess(1);
            return backLoginResult;
        }else {
            backLoginResult.setIsSuccess(0);
            return backLoginResult;
        }
    }
}
