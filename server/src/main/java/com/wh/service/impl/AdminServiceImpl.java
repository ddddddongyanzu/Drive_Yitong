package com.wh.service.impl;

import com.wh.entity.Admin;
import com.wh.dao.AdminDao;
import com.wh.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Admin)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 15:13:15
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;


    @Override
    public Admin queryByEntity(Admin admin) {
        return this.adminDao.queryByEntity(admin);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param aId 主键
     * @return 实例对象
     */
    @Override
    public Admin queryById(Integer aId) {
        return this.adminDao.queryById(aId);
    }

    /**
     * 查询多条数据
     *

     * @return 对象列表
     */
    @Override
    public List<Admin> queryAll() {
        return this.adminDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin insert(Admin admin) {
        this.adminDao.insert(admin);
        return admin;
    }

    /**
     * 修改数据
     *
     * @param admin 实例对象
     * @return 实例对象
     */
    @Override
    public Admin update(Admin admin) {
        this.adminDao.update(admin);
        return this.queryById(admin.getAId());
    }

    /**
     * 通过主键删除数据
     *
     * @param aId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer aId) {
        return this.adminDao.deleteById(aId) > 0;
    }
}
