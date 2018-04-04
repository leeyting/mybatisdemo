package com.mybatis.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mybatis.demo.dao.annotation.UserMapperAnnotation;
import com.mybatis.demo.dao.xmlmapper.UserMapper;
import com.mybatis.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/03 11:16
 */

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserMapperAnnotation userMapperAnnotation;

    @Override
    public int addUser(User user) {

        return userMapper.insertSelective(user);
    }

    /*
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
//        Page page = (Page) userMapper.selectAllUser();
        Page page = (Page) userMapperAnnotation.selectAllUser();
        return page.getResult();
    }

    @Override
    public List<User> findUserById(int id) {
        return userMapperAnnotation.findUserById(id);
    }

    @Override
    public List<User> findUserByIds(List<Integer> ids) {
        return userMapperAnnotation.findUserByIds(ids);
    }

    @Override
    public List<User> findAllUserByCondition(User user) {
        return userMapperAnnotation.findAllUserByCondition(user);
    }
}
