package com.mybatis.demo.service;

import com.mybatis.demo.entity.User;

import java.util.List;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/04 09:07
 */

public interface UserService {

    int addUser(User user);

    List<User> findAllUser(int pageNum, int pageSize);

    List<User> findUserById(int id);

    List<User> findUserByIds(List<Integer> ids);

    List<User> findAllUserByCondition(User user);
}
