package com.wh.dao;

import com.wh.entity.Price;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Price)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-27 15:13:54
 */
public interface PriceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    Price queryById(Integer pId);

    /**
     * 查询指定行数据
     *

     * @return 对象列表
     */
    List<Price> queryAll();


//    /**
//     * 通过实体作为筛选条件查询
//     *
//     * @param price 实例对象
//     * @return 对象列表
//     */
//    List<Price> queryAll(Price price);

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 影响行数
     */
    int insert(Price price);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Price> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Price> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Price> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<Price> entities);

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 影响行数
     */
    int update(Price price);

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 影响行数
     */
    int deleteById(Integer pId);

}

