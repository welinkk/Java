package com.kk.producer.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestService {

    @Resource
    private AmqpTemplate amqpTemplate;
    public void sendMsg(String msg){
        if (msg.startsWith("q_")){
            //1.发送消息到消息队列
            amqpTemplate.convertAndSend("queue1",msg);
        }else if (msg.startsWith("f_")){
            //发送消息到交换机(订阅模式)
            amqpTemplate.convertAndSend("ex1","",msg);
        }else {
            if (msg.startsWith("r_a")){
                //发送到交换机（路由模式）
                amqpTemplate.convertAndSend("ex2","a",msg);
            }else if (msg.startsWith("r_b")){
                //发送到交换机（路由模式）
                amqpTemplate.convertAndSend("ex2","b",msg);
            }
        }

    }
}
