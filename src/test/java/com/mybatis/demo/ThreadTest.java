package com.mybatis.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: liyao
 * @Description: 使用CountDownLatch模拟多线程并发
 * @Date: Created in 2018/04/23 16:24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Configuration
public class ThreadTest {

    private static final int THREAD_NUM = 1000;
    private CountDownLatch countDownLatch = null;

    @Before
    public void start() {
        countDownLatch = new CountDownLatch(THREAD_NUM);
        System.out.println("初始化CountDownLatch");
    }

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread thread = new Thread(new UserRequest());
            threads[i] = thread;
            thread.start();
            countDownLatch.countDown();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    @After
    public void after() {
        System.out.println("测试结束");

    }

    private class UserRequest implements Runnable {

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("当前线程：" + Thread.currentThread());
        }
    }


}
