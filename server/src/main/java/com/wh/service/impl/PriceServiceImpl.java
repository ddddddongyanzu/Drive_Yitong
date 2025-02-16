package com.wh.service.impl;

import com.wh.entity.Price;
import com.wh.dao.PriceDao;
import com.wh.service.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Price)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 15:13:54
 */
@Service("priceService")
public class PriceServiceImpl implements PriceService {
    @Resource
    private PriceDao priceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    @Override
    public Price queryById(Integer pId) {
        return this.priceDao.queryById(pId);
    }

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    @Override
    public List<Price> queryAll() {
        return this.priceDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    @Override
    public Price insert(Price price) {
        this.priceDao.insert(price);
        return price;
    }

    /**
     * 修改数据
     *
     * @param price 实例对象
     * @return 实例对象
     */
    @Override
    public Price update(Price price) {
        this.priceDao.update(price);
        return this.queryById(price.getpId());
    }

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pId) {
        return this.priceDao.deleteById(pId) > 0;
    }
}
