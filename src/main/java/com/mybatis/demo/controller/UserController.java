package com.mybatis.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mybatis.demo.aop.annotation.LoginAnnotation;
import com.mybatis.demo.base.context.SpringContextAware;
import com.mybatis.demo.base.redis.cluster.RedisClusterUtil;
import com.mybatis.demo.base.redis.standalone.RedisUtil;
import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.result.ResultV1;
import com.mybatis.demo.base.thread.AsyncTaskService;
import com.mybatis.demo.entity.User;
import com.mybatis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/04/02 10:15
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisClusterUtil redisClusterUtil;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/add")
    public int addUser(User user) {
        return userService.addUser(user);
    }

    @GetMapping("/all/{pageNum}/{pageSize}")
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    @GetMapping("/findUserById/{id}")
    public List<User> findUserById(@PathVariable("id") int id) {
        return userService.findUserById(id);
    }

    @PostMapping("/search")
    public List<User> search(User user) {
        return userService.findAllUserByCondition(user);
    }

    @PostMapping("/findUserByIds")
    public List<User> findUserByIds(@RequestBody List<Integer> ids) {
        return userService.findUserByIds(ids);
    }

    @LoginAnnotation(name = "登录验证")
    @GetMapping("/info")
    public ResultV1 info() {
        Map map = new HashMap();
        map.put("id", 123);
        map.put("nick", "阿发");
        redisUtil.set("name", JSON.toJSONString(map));
        logger.info("name:{}", JSON.toJSONString(map));
        String name = redisUtil.get("name");
        return ResultFactory.SuccessV1(JSON.parse(name));
    }

    @GetMapping("/setter")
    public ResultV1 setter() {
        redisClusterUtil.set("rc", "name", "123和");
        return ResultFactory.SuccessV1("成功");
    }

    @GetMapping("/getter")
    public ResultV1 getter() {
        return ResultFactory.SuccessV1(redisClusterUtil.get("rc", "name"));
    }

    @GetMapping("/asynService")
    public ResultV1 asynService() {
        AsyncTaskService taskService = SpringContextAware.getBean(AsyncTaskService.class);
        taskService.produceTask();
        taskService.produceTask();
        taskService.produceTask();
        taskService.produceTask();
        taskService.comsumerTask();
        taskService.comsumerTask();
        taskService.comsumerTask();
        taskService.comsumerTask();
        return ResultFactory.SuccessV1("success");
    }
}
