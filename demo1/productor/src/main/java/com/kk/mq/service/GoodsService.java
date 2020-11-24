package com.kk.mq.service;

import com.kk.mq.beans.Goods;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsService {

    @Resource
    private AmqpTemplate amqpTemplate;
    public void SendGoodsMsg(Goods goods){
        amqpTemplate.convertAndSend("","queue2",goods);
    }
}
