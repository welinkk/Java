package com.kk.mq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.mq.beans.Goods;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.Serializable;

@Service
public class GoodsService {

    @Resource
    private AmqpTemplate amqpTemplate;

//    public void  SendDGoodsMsg(Goods goods){
//
////        byte[] bytes=SerializationUtils.serialize(goods);
//        amqpTemplate.convertAndSend("","queue2",goods);
//    }
    public void  SendDGoodsMsg(Goods goods) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(goods);
        amqpTemplate.convertAndSend("","queue2",msg);
    }
}
