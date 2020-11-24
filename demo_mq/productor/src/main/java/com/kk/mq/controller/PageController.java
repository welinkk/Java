package com.kk.mq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/goodsAdd.html")
    public String goodsAdd(){
        return "goodsAdd";
    }
}
