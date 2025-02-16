package com.wh.service;

import com.wh.entity.Price;

import java.util.List;

/**
 * (Price)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 15:13:54
 */
public interface PriceService {

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    Price queryById(Integer pId);

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    List<Price> queryAll();

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    Price insert(Price price);

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    Price update(Price price);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pId);

}
