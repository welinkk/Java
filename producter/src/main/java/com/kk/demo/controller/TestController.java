package com.kk.demo.controller;

import com.kk.demo.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TestService testService;

    @RequestMapping("test")
    public String SendMsg(String msg){
        testService.sendMsg(msg);
        return "success";
    }
}
