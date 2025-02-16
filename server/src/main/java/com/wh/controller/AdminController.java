package com.wh.controller;

import com.wh.entity.Admin;
import com.wh.service.AdminService;
import com.wh.vo.UpdateAdminvo;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author makejava
 * @since 2021-04-27 15:13:15
 */
@RestController
@RequestMapping(value = "admin",method = {RequestMethod.POST,RequestMethod.GET})
public class AdminController {
    /**
     * 服务对象
     */
    @Resource
    private AdminService adminService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Admin selectOne(Integer id) {
        return this.adminService.queryById(id);
    }

    @RequestMapping("update")
    public int updateAdmin(@RequestBody UpdateAdminvo updateAdminvo) {
        Admin admin = new Admin();
        admin.setUserName(updateAdminvo.getUserName());
        admin.setPassword(updateAdminvo.getOldPwd());
        System.out.println("-----------------1---------------");
//       验证原来密码是否正确
        Admin authenticationAdmin = this.adminService.queryByEntity(admin);
        if (authenticationAdmin != null && !updateAdminvo.getNewPwd().equals("")) {
            authenticationAdmin.setPassword(updateAdminvo.getNewPwd());
            this.adminService.update(authenticationAdmin);
            return 1;
        }else {
            return 0;
        }

    }
}
