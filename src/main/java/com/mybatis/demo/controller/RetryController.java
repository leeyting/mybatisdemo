package com.mybatis.demo.controller;

import com.mybatis.demo.base.result.ResultFactory;
import com.mybatis.demo.base.result.ResultV1;
import com.mybatis.demo.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/07/15 22:41
 */

@RestController
@RequestMapping(value = "/retry")
public class RetryController {

    @Autowired
    private RetryService retryService;

    @GetMapping("/call")
    public ResultV1 call() {
        try {
//            return retryService.call();
            retryService.call();
            return ResultFactory.SuccessV1("成功");
        } catch (Exception e) {
            return ResultFactory.FailedV1(e.getMessage());
        }
    }
}
