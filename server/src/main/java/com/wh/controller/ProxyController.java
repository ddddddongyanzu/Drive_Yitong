package com.wh.controller;

import com.github.pagehelper.PageHelper;
import com.wh.entity.Proxy;
import com.wh.entity.Student;
import com.wh.entity.User;
import com.wh.service.ProxyService;
import com.wh.service.StudentService;
import com.wh.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Proxy)表控制层
 *
 * @author makejava
 * @since 2021-04-27 15:14:14
 */
@RestController
@RequestMapping(value = "proxy", method = {RequestMethod.POST,RequestMethod.GET})
public class ProxyController {
    /**
     * 服务对象
     */
    @Resource
    private ProxyService proxyService;

    @Resource
    private StudentService studentService;

    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Proxy selectOne(@ApiParam("代理人id")Integer id) {
        return this.proxyService.queryById(id);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param uId 主键
     * @return 单条数据
     */
    @GetMapping("selectMyStudent")
    public List<Student> selectMyStudent(@ApiParam("用户id")Integer uId) {
        Proxy proxy = this.proxyService.queryByUid(uId);
        if(proxy==null){
            return null;
        }else {
            List<Student> students = this.studentService.selectMyStudent(proxy.getpId());
            return students.isEmpty()?null:students;
        }
    }

    @GetMapping("/selectBasePriceByUid")
    public Double selectByUid(@ApiParam("用户id")Integer uId) {
        Proxy proxy = this.proxyService.queryByUid(uId);
        return proxy!=null?proxy.getBasePrice():0;
    }


    //新增经理
    @PostMapping("/add")
    public int register(@RequestBody @ApiParam("代理人对象")Proxy proxy) {
//        System.out.println("uId = " + uId);
        System.out.println(proxy);
//        将其插入到proxy表中
        this.proxyService.insert(proxy);
//        将user权限role设置为1
        User user = this.userService.queryById(proxy.getuId());
        user.setRole(1);
        this.userService.update(user);
        return 1;
    }

    @RequestMapping("/delete")
    public int deleteProxy(@ApiParam("用户id")int uId,@ApiParam("代理人id")int pId) {
        this.proxyService.deleteById(pId);
        User user = this.userService.queryById(uId);
        user.setRole(0);
        this.userService.update(user);
        return 1;
    }

    @RequestMapping("/update")
    public int updateProxy(@Validated @RequestBody @ApiParam("代理人对象")Proxy proxy) {
        System.out.println("修改的用户信息为");
        System.out.println(proxy);
        this.proxyService.update(proxy);
        return 1;
    }


    //    全局模糊搜索
    @RequestMapping("/selectAllByCondition")
    public List<Proxy> getAllByCondition(@ApiParam("关键词")String keywords, @ApiParam("当前页")int pageNum, @ApiParam("页面大小")int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return this.proxyService.selectAllByCondition(keywords);
    }

    @RequestMapping("/selectCount")
    public int getCount() {
        return this.proxyService.queryCount();
    }
}
