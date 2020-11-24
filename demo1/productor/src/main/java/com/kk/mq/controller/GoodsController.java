package com.kk.mq.controller;

import com.kk.mq.beans.Goods;
import com.kk.mq.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {


    @Resource
    private GoodsService goodsService;
    @RequestMapping("/add")
    public String goodsAdd(Goods goods){
        System.out.println("我是controller");
        goodsService.SendGoodsMsg(goods);
        return "goods add success";
    }
}
