package com.wh.service.impl;

import com.wh.entity.Student;
import com.wh.dao.StudentDao;
import com.wh.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Student)表服务实现类
 *
 * @author makejava
 * @since 2021-04-27 20:30:34
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;
    /**
     * 通过ID查询单条数据
     *
     * @param pId 主键
     * @return 对象列表
     */
    @Override
    public List<Student> selectMyStudent(int pId) {
        return this.studentDao.selectMyStudent(pId);
    }

    @Override
    public List<Student> selectAllByCondition(String keywords) {
        return this.studentDao.selectAllByCondition(keywords);
    }
    /**
     * 通过ID查询单条数据
     *
     *
     * @return 实例对象
     */
    @Override
    public Student queryByUId(Integer uId) {
        return this.studentDao.queryByUId(uId);
    }

    @Override
    public int queryCount() {
        return this.studentDao.queryCount();
    }

    /**
     * 通过ID查询单条数据
     *
     * @param sId 主键
     * @return 实例对象
     */
    @Override
    public Student queryById(Integer sId) {
        return this.studentDao.queryById(sId);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Student> queryAllByLimit() {
        return this.studentDao.queryAllByLimit();
    }

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student insert(Student student) {
        this.studentDao.insert(student);
        return student;
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student update(Student student) {
        this.studentDao.update(student);
        return this.queryById(student.getSId());
    }

    /**
     * 通过主键删除数据
     *
     * @param sId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sId) {
        return this.studentDao.deleteById(sId) > 0;
    }
}
