package com.kk.mq.service;

import com.kk.mq.beans.Goods;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MQService {
    @Resource
    private AmqpTemplate amqpTemplate;
    public void sendGoodsToMq(Goods goods){
        //消息队列只可以发送字符串，字节数组，序列化的对象
        amqpTemplate.convertAndSend("","queue2",goods);
    }
}
