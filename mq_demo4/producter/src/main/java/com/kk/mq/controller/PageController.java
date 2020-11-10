package com.kk.mq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/goods_add.html")
    public String goodAdd(){
        return "goods_add";
    }
}
