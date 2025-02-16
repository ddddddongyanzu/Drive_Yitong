package com.wh.dao;

import com.wh.entity.Proxy;
import com.wh.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Proxy)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 15:14:13
 */
public interface ProxyDao {

    //全局模糊搜索
    List<Proxy> selectAllByCondition(String keywords);

    //查询用户数量
    int queryCount();
    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    Proxy queryById(Integer pId);

    Proxy queryByUid(Integer uId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<Proxy> queryAll();


//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param proxy 实例对象
//     * @return 对象列表
//     */
//    List<Proxy> queryAll(Proxy proxy);

    /**
     * 新增数据
     *
     * @param proxy 实例对象
     * @return 影响行数
     */
    int insert(Proxy proxy);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Proxy> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Proxy> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Proxy> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Proxy> entities);

    /**
     * 修改数据
     *
     * @param proxy 实例对象
     * @return 影响行数
     */
    int update(Proxy proxy);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 影响行数
     */
    int deleteById(Integer pId);

}

