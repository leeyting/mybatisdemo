package com.mybatis.demo.base.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author: liyao
 * @Description: 多线程异步服务
 * @Date: Created in 2018/04/12 14:03
 */

@Component
public class AsyncTaskService {
    @Async("myTaskAsyncPool")
    public void produceTask(){
        System.out.println(Thread.currentThread().getName() + ": 任务生产...");
    }
    @Async("myTaskAsyncPool")
    public void comsumerTask(){
        System.out.println(Thread.currentThread().getName() + ": 任务消费...");
    }
}
