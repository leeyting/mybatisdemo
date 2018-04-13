package com.mybatis.demo.base.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @Author: liyao
 * @Description: 消息消费者
 * @Date: Created in 2018/04/13 10:37
 */

@Component
public class Consumer {

    @JmsListener(destination = "test.queue")
    public void receiveTestQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }

    @JmsListener(destination = "mytest.queue")
    @SendTo("out.queue")
    public String receiveMyTestQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
        return "Consumer返回的报文为：" + text;
    }
}
