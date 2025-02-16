package com.wh.service;

import com.wh.entity.Student;
import com.wh.entity.User;

import java.util.List;

/**
 * (Student)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 20:30:33
 */
public interface StudentService {

    List<Student> selectMyStudent(int pId);

    List<Student> selectAllByCondition(String keywords);
    /**
     * 通过ID查询单条数据
     *
     *
     * @return 实例对象
     */
    Student queryByUId(Integer uId);

    int queryCount();

    /**
     * 通过ID查询单条数据
     *
     * @param sId 主键
     * @return 实例对象
     */
    Student queryById(Integer sId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Student> queryAllByLimit();

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param sId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sId);

}
