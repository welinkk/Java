package com.kk.mq.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
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
    public String add(Goods goods) throws JsonProcessingException {
        goodsService.SendDGoodsMsg(goods);
        return "goodsAdd";
    }

}
