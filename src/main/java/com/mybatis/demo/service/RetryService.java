package com.mybatis.demo.service;

import com.mybatis.demo.base.exception.CustomRuntimeException;
import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.result.ResultV1;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

/**
 * @Author: liyao
 * @Description: 重试
 * @Date: Created in 2018/07/15 22:39
 */

@Service
public class RetryService {

    /**
     * @throws Exception
     * @Retryable注解 被注解的方法发生异常时会重试
     * value:指定发生的异常进行重试
     * include:和value一样，默认空，当exclude也为空时，所有异常都重试
     * exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试
     * maxAttemps:重试次数，默认3
     * backoff:重试补偿机制，默认没有
     * @Backoff注解 delay:指定延迟后重试
     * multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒
     * @Recover 当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
     */
    @Retryable(value = {CustomRuntimeException.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000l, multiplier = 1))
    @Async
    public ResultV1 call() throws Exception {
        doPost();
        return ResultFactory.SuccessV1("成功");
    }

    @Recover
    public ResultV1 recover(CustomRuntimeException e) {
        System.out.println(e.getMessage());
        return ResultFactory.FailedV1(e.getMessage());
    }

    private ResultV1 doPost() throws Exception {
        try {
            System.out.println("do something...");
            if (true) {
                throw new ConnectException("RPC调用异常");
            }
        } catch (ConnectException e) {
            throw new CustomRuntimeException(e.getMessage());
        } catch (RemoteAccessException e) {
            throw new CustomRuntimeException(e.getMessage());
        }

        return ResultFactory.FailedV1("失败");
    }
}
