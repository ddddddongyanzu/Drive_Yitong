package com.wh.controller;

import com.github.pagehelper.PageHelper;
import com.wh.entity.Student;
import com.wh.entity.User;
import com.wh.service.UserService;
import com.wh.vo.UserSelectOnevo;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-04-27 15:14:46
 */
@RestController
@RequestMapping(value = "user", method = {RequestMethod.POST,RequestMethod.GET})
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @Param  id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public User selectOne(@RequestBody(required = false) @ApiParam("用户id") Integer id) {
        return this.userService.queryById(id);
    }

    @RequestMapping("/selectAll")
    public List<User> getAll(@ApiParam("当前页")int pageNum, @ApiParam("页面大小")int pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
        } catch (Exception e) {
            PageHelper.startPage(1, 8);
        }
        return this.userService.queryAll();
    }
    @RequestMapping("/selectByOpenid")
    public User selectByOpenid(@RequestBody(required = false) @ApiParam("微信号") String openid)
    {return this.userService.queryByOpenid(openid);}


    @RequestMapping("/selectCount")
    public int getCount() {
        return this.userService.queryCount();
    }


    //    全局模糊搜索
    @RequestMapping("/selectAllByCondition")
    public List<User> getAllByCondition(@ApiParam("关键词")String keywords, @ApiParam("当前页")int pageNum, @ApiParam("页面大小")int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return this.userService.selectAllByCondition(keywords);
    }

    @RequestMapping("/update")
    public int updateStudent(@Validated @RequestBody @ApiParam("用户对象")User user) {
        System.out.println(user);
        this.userService.update(user);
        return 1;
    }
}
