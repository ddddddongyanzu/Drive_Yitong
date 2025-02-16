package com.wh.service;

import com.wh.entity.Banner;

import java.util.List;

/**
 * (Banner)表服务接口
 *
 * @author makejava
 * @since 2021-04-27 15:13:47
 */
public interface BannerService {

    /**
     * 通过ID查询单条数据
     *
     * @param bId 主键
     * @return 实例对象
     */
    Banner queryById(Integer bId);

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    List<Banner> queryAll();

    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    Banner insert(Banner banner);

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    Banner update(Banner banner);

    /**
     * 通过主键删除数据
     *
     * @param bId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer bId);

}
