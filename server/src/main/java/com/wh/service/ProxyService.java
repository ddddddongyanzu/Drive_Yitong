package com.wh.service;

import com.wh.entity.Proxy;
import com.wh.entity.Student;

import java.util.List;

/**
 * (Proxy)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 15:14:13
 */
public interface ProxyService {


    List<Proxy> selectAllByCondition(String keywords);

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
     * 查询多条数据
     *

     * @return 对象列表
     */
    List<Proxy> queryAll();

    /**
     * 新增数据
     *
     * @param proxy 实例对象
     * @return 实例对象
     */
    Proxy insert(Proxy proxy);

    /**
     * 修改数据
     *
     * @param proxy 实例对象
     * @return 实例对象
     */
    Proxy update(Proxy proxy);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pId);

}
