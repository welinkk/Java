package com.kk.producer.controller;

import com.kk.producer.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("test")
    public String test(String msg){
        testService.sendMsg(msg);
        return "success";
    }
}
