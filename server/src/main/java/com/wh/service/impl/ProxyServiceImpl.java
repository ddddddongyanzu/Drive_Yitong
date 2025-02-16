package com.wh.service.impl;

import com.wh.entity.Proxy;
import com.wh.dao.ProxyDao;
import com.wh.service.ProxyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Proxy)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 15:14:14
 */
@Service("proxyService")
public class ProxyServiceImpl implements ProxyService {
    @Resource
    private ProxyDao proxyDao;

    @Override
    public List<Proxy> selectAllByCondition(String keywords) {
        return this.proxyDao.selectAllByCondition(keywords);
    }

    @Override
    public int queryCount() {
        return this.proxyDao.queryCount();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 实例对象
     */
    @Override
    public Proxy queryById(Integer pId) {
        return this.proxyDao.queryById(pId);
    }

    @Override
    public Proxy queryByUid(Integer uId) {
        return this.proxyDao.queryByUid(uId);
    }


    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    @Override
    public List<Proxy> queryAll() {
        return this.proxyDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param proxy 实例对象
     * @return 实例对象
     */
    @Override
    public Proxy insert(Proxy proxy) {
        this.proxyDao.insert(proxy);
        return proxy;
    }

    /**
     * 修改数据
     *
     * @param proxy 实例对象
     * @return 实例对象
     */
    @Override
    public Proxy update(Proxy proxy) {
        this.proxyDao.update(proxy);
        return this.queryById(proxy.getpId());
    }

    /**
     * 通过主键删除数据
     *
     * @param pId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pId) {
        return this.proxyDao.deleteById(pId) > 0;
    }
}
