package com.wh.service;

import com.wh.entity.User;

import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 15:14:46
 */
public interface UserService {


    int queryCount();

    List<User> selectAllByCondition(String keywords);

    User queryByOpenid(String openid);

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    User queryById(Integer uId);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<User> queryAll();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uId);

}
