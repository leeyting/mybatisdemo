package com.mybatis.demo.base.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liyao
 * @Description: 定时器
 * @Date: Created in 2018/05/02 17:28
 */

@Component
public class ScheduledTimer {

    @Scheduled(initialDelay = 8000, fixedDelay = 5000)
    public void nowDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务执行，现在时间是 : " + format.format(new Date()));
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void cron() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务执行cron，现在时间是 : " + format.format(new Date()));
    }
}
