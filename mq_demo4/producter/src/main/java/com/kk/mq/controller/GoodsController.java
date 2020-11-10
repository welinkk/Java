package com.kk.mq.controller;

import com.kk.mq.beans.Goods;
import com.kk.mq.service.MQService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private MQService mqService;

    @RequestMapping("/add")
    public String add(Goods goods){
        //完成了商品添加操作 ---service
        //将goods对象通过消息队列传递给 consumer
        mqService.sendGoodsToMq(goods);
        return "goods_add";
    }

}
