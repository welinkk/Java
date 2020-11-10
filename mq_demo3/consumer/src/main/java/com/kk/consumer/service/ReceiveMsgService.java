package com.kk.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "queue1")
public class ReceiveMsgService {

    @RabbitHandler
    public void receiverMsg(String msg){
        System.out.println("接受消息："+msg);
    }
}
