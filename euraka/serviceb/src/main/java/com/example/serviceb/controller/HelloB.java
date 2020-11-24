package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloB {

    @RequestMapping("/hi")
    public String helloB(){
        return "我是service B";
    }
}
