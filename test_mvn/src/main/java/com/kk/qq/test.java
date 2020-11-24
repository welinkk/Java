package com.kk.qq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class test {

    @RequestMapping("/index.html")
    public static void main(String[] args) {
        System.out.println("hello");
    }
}
