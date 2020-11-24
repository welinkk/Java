package com.kk.mvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DockerHello {

    @RequestMapping("/index.html")
    public void SayHello(){

    }
}
