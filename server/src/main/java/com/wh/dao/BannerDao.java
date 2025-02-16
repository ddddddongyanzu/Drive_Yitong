package com.wh.dao;

import com.wh.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Banner)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 15:13:47
 */
public interface BannerDao {

    /**
     * 通过ID查询单条数据
     *
     * @param bId 主键
     * @return 实例对象
     */
    Banner queryById(Integer bId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<Banner> queryAll();


//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param banner 实例对象
//     * @return 对象列表
//     */
//    List<Banner> queryAll(Banner banner);

    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 影响行数
     */
    int insert(Banner banner);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Banner> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Banner> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Banner> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Banner> entities);

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 影响行数
     */
    int update(Banner banner);

    /**
     * 通过主键删除数据
     *
     * @param bId 主键
     * @return 影响行数
     */
    int deleteById(Integer bId);

}

