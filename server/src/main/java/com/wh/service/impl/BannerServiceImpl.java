package com.wh.service.impl;

import com.wh.entity.Banner;
import com.wh.dao.BannerDao;
import com.wh.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Banner)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 15:13:47
 */
@Service("bannerService")
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDao bannerDao;

    /**
     * 通过ID查询单条数据
     *
     * @param bId 主键
     * @return 实例对象
     */
    @Override
    public Banner queryById(Integer bId) {
        return this.bannerDao.queryById(bId);
    }

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    @Override
    public List<Banner> queryAll() {
        return this.bannerDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    public Banner insert(Banner banner) {
        this.bannerDao.insert(banner);
        return banner;
    }

    /**
     * 修改数据
     *
     * @param banner 实例对象
     * @return 实例对象
     */
    @Override
    public Banner update(Banner banner) {
        this.bannerDao.update(banner);
        return this.queryById(banner.getBId());
    }

    /**
     * 通过主键删除数据
     *
     * @param bId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer bId) {
        return this.bannerDao.deleteById(bId) > 0;
    }
}
