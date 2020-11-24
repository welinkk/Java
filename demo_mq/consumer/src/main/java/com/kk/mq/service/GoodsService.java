package com.kk.mq.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.mq.beans.Goods;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

@Component
@RabbitListener(queues = "queue2")
public class GoodsService {

    @RabbitHandler
    public void getMsg(String msg) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Goods goods= objectMapper.readValue(msg,Goods.class);
        System.out.println("String-----"+msg);
    }
    @RabbitHandler
    public void getMsg(byte[] bs){
        Goods goods= (Goods) SerializationUtils.deserialize(bs);
        System.out.println("bytes-------"+goods);
    }
    @RabbitHandler
    public void getMsg(Goods goods){
        System.out.println("对象-------"+goods);
    }
}
