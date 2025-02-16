package com.wh.service.impl;

import com.wh.entity.User;
import com.wh.dao.UserDao;
import com.wh.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 15:14:46
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Override
    public int queryCount() {
        return this.userDao.queryCount();
    }

    @Override
    public List<User> selectAllByCondition(String keywords) {
        return this.userDao.selectAllByCondition(keywords);
    }

    @Override
    public User queryByOpenid(String openid) {
        return this.userDao.queryByOpenid(openid);
    }


    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uId) {
        return this.userDao.queryById(uId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryAll() {
        return this.userDao.queryAll();
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uId) {
        return this.userDao.deleteById(uId) > 0;
    }
}
