package mq.service;

import mq.utils.Mq_utils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.Scanner;

public class SendMsg {
    public static void main(String[] args) throws Exception {

        System.out.println("请输入消息：");
        Scanner scanner = new Scanner(System.in);
        String msg = null;

        while (!"quit".equals(msg = scanner.nextLine())){
            Connection connection = Mq_utils.getConnection(); //相当于jdbc的链接
            Channel channel = connection.createChannel();//相当于jdbc的statement

            if (msg.startsWith("a")){
                //第二个人参数是绑定的key
                channel.basicPublish("ex2","a", null,msg.getBytes());

                System.out.println("发送："+msg);
            }else if (msg.startsWith("b")){
                 channel.basicPublish("ex2","b", null,msg.getBytes());

                System.out.println("发送："+msg);
            }


            channel.close();

            connection.close();

        }

    }
}
