package com.kk.mq.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
@Component
@Service
@RabbitListener(queues = "queue1")
public class ReceiveMsgService {

    @RabbitHandler
    public void receiveMsg(String msg){
        System.out.println("String:------"+msg);
    }
    @RabbitHandler
    public void receiveMsg(byte[] msg){
        System.out.println("byte[]:-------"+new String(msg));
    }

}
