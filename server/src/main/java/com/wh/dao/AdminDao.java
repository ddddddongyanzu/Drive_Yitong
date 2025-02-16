package com.wh.dao;

import com.wh.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Admin)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 15:13:13
 */
public interface AdminDao {


    Admin queryByEntity(Admin admin);

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    Admin queryById(Integer aId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<Admin> queryAll();


//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param admin 实例对象
//     * @return 对象列表
//     */
//    List<Admin> queryAll(Admin admin);

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Admin> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Admin> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Admin> entities);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 影响行数
     */
    int update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 影响行数
     */
    int deleteById(Integer aId);

}

