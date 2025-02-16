package com.wh.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.wh.entity.Proxy;
import com.wh.entity.Student;
import com.wh.entity.User;
import com.wh.service.ProxyService;
import com.wh.service.StudentService;
import com.wh.service.UserService;
import com.wh.vo.DeleteStudentvo;
import com.wh.vo.Registervo;
import com.wh.vo.Studentvo;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * (Student)表控制层
 *
 * @author makejava
 * @since 2021-04-27 20:30:34
 */
@RestController
@RequestMapping(value = "student", method = {RequestMethod.POST, RequestMethod.GET})
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    @Resource
    private UserService userService;

    @Resource
    private ProxyService proxyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Student selectOne(@ApiParam("学员id") Integer id) {
        return this.studentService.queryById(id);
    }

    @RequestMapping("selectByUId")
    public Student isRegister(@ApiParam("用户id") Integer uId) {
        User user = this.userService.queryById(uId);
        Student student = new Student();
//     判断该用户是否报名
        if (user != null) {
            if (user.getIsRegister() == 1) {
                return this.studentService.queryByUId(uId);
            }
        }
        return null;
    }


    @RequestMapping("/add")
    @ResponseBody
    public Student register(@RequestBody Registervo registervo) {
        //         将登陆的用户信息拿到封装到user中，将报名信息拿到封装到student中
        System.out.println(registervo.getStudent());
                Student newStudent = JSON.parseObject(registervo.getStudent(), Student.class);
        System.out.println("1");
                newStudent.setUId(registervo.getUId()); //        将小程序登录用户的id赋值给新插入的学员
        System.out.println("2");
                newStudent.setRegisterTime(new Date()); //         设置报名时间
        System.out.println("3");
                newStudent.setCarType(registervo.getCarType()); //设置车型
                newStudent.setPayAmount(registervo.getPayAmount());
                User user = userService.queryById(registervo.getUId());
                newStudent.setOpenid(user.getOpenid());
                int pId=0;
                if (registervo.getProxy_uid() != 0) {
                    Proxy proxy = proxyService.queryByUid(registervo.getProxy_uid());
                    pId=proxy.getpId();
                }
                newStudent.setPId(pId);
        //        报名成功 ，将该用户的权限设置为2 也就是学员
                user.setIsRegister(1);
        //        if (pId != null) {
        ////          若果是代理人介绍的，将代理人的id赋值给学员
        //            newStudent.setPId(Integer.parseInt(pId));
        //        } else {
        ////           没有代理人将pid设置为0
        //            newStudent.setPId(0);
        //        }
                System.out.println("报名的学员信息为-----");
                System.out.println(newStudent);
                userService.update(user);
                return this.studentService.insert(newStudent);
            }

    @RequestMapping("/delete")
    public int deleteStudent(@ApiParam("用户id") Integer uId,@ApiParam("学员id") Integer sId) {
//        将学员id为sid的从学员表中删掉
        this.studentService.deleteById(sId);
        User user = this.userService.queryById(uId);
//        将用户的角色设置为1 表示未报名
        user.setIsRegister(0);
        this.userService.update(user);
        return 1;
    }

    @RequestMapping("/update")
    public int updateStudent(@Validated @RequestBody @ApiParam("学员") Student student) {
        System.out.println(student);
        this.studentService.update(student);
        return 1;
    }

    //    全局模糊搜索
    @RequestMapping("/selectAllByCondition")
    public List<Student> getAllByCondition(@ApiParam("关键词") String keywords, @ApiParam("当前页") int pageNum, @ApiParam("页面大小") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.studentService.selectAllByCondition(keywords);
    }

    @RequestMapping("/selectCount")
    public int getCount() {
        return this.studentService.queryCount();
    }
}
