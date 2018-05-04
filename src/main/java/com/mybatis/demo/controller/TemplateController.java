package com.mybatis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/04 13:56
 */

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("email", "fafg@163.com");
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
