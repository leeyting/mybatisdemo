package com.mybatis.demo.controller;

import com.mybatis.demo.entity.User;
import com.mybatis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/02 10:15
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public int addUser(User user){
        return userService.addUser(user);
    }

    @GetMapping("/all/{pageNum}/{pageSize}")
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        return userService.findAllUser(pageNum,pageSize);
    }

    @GetMapping("/findUserById/{id}")
    public List<User> findUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    @PostMapping("/search")
    public List<User> search(User user){
        return userService.findAllUserByCondition(user);
    }

    @PostMapping("/findUserByIds")
    List<User> findUserByIds(@RequestBody List<Integer> ids){
        return userService.findUserByIds(ids);
    }
}
