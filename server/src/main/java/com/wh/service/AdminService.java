package com.wh.service;

import com.wh.entity.Admin;

import java.util.List;

/**
 * (Admin)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 15:13:14
 */
public interface AdminService {

    Admin queryByEntity(Admin admin);

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    Admin queryById(Integer aId);

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    List<Admin> queryAll();

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin insert(Admin admin);

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    Admin update(Admin admin);

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer aId);

}
