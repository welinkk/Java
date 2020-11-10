package com.kk.mq.service;

import com.kk.mq.utils.Mq_utils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SendMsg {
    public static void main(String[] args) throws Exception {
        String msg = "Hello 大傻";
        Connection connection = Mq_utils.getConnection(); //相当于jdbc的链接
        Channel channel = connection.createChannel();//相当于jdbc的statement

        //定义队列（使用Java代码在MQ中新建一个队列）
        //参数1：消息队列名称
        //参数2：队列中的数据是否持久化：
        //参数3：是否排外（）
        //参数4：自动删除
        //参数5：设置当前队列的参数
        //channel.queueDeclare("queue2",false,false,false,null);

        //参数1：交换机名称，如果直接发送信息到队列，则交换机名称为""
        //参数2:目标队列名称
        //参数3：设置消息的属性（设置过期时间：10）
        //参数4：消息的内容
        channel.basicPublish("","queue1", null,msg.getBytes());

        System.out.println("发送："+msg);

        channel.close();

        connection.close();

    }
}
