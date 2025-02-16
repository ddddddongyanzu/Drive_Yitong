package com.wh.dao;

import com.wh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 15:14:46
 */
public interface UserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    User queryById(Integer uId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<User> queryAll();


//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param user 实例对象
//     * @return 对象列表
//     */
//    List<User> queryAll(User user);

//查询用户数量
     int queryCount();

//全局模糊搜索
    List<User> selectAllByCondition(String keywords);

//微信号搜索
    User queryByOpenid(String openid);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 影响行数
     */
    int deleteById(Integer uId);

}

