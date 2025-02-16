package com.wh.dao;

import com.wh.entity.Student;
import com.wh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 20:30:33
 */
public interface StudentDao {
    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<Student> selectMyStudent(Integer pId);

    //全局模糊搜索
    List<Student> selectAllByCondition(String keywords);

    //查询用户数量
    int queryCount();

    /**
     * 通过ID查询单条数据
     *
     * @param sId 主键
     * @return 实例对象
     */
    Student queryById(Integer sId);
    /**
     * 通过ID查询单条数据
     *
     *
     * @return 实例对象
     */
    Student queryByUId(Integer uId);



    /**
     * 查询指定行数据
     *
     * @return 对象列表
     */
    List<Student> queryAllByLimit();


    /**
     * 通过实体作为筛选条件查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryAll(Student student);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Student> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Student> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Student> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Student> entities);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param sId 主键
     * @return 影响行数
     */
    int deleteById(Integer sId);

}

