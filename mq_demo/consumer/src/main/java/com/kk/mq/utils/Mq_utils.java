package com.kk.mq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Mq_utils { public static Connection getConnection(){
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.mq连接信息
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("host1");
        factory.setUsername("kk");
        factory.setPassword("admin123");
        //3.通过工厂对象获取mq链接对象
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public static void main(String[] args) throws IOException, TimeoutException {
//        System.out.println(getConnection());
//
//    }
}
