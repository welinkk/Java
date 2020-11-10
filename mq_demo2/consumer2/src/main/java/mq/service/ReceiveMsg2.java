package mq.service;

import mq.utils.Mq_utils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveMsg2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = Mq_utils.getConnection();
        Channel channel = connection.createChannel();

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body就是从队列中获取的数据
                String msg = new String(body);
                System.out.println("consumer-2-接受"+msg);
            }
        };
        channel.basicConsume("queue4",true,consumer);

    }
}
