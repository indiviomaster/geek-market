package com.geekbrains.rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class SenderApp {
    private final static String QUEUE_NAME = "RabbitTest";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()){
            channel.queueDeclare(QUEUE_NAME,false,false,false, null);
            String msg = "Hello From RabbitMQ";
            channel.basicPublish("",QUEUE_NAME,null, msg.getBytes());
            System.out.println("sent msg: "+msg);
        }
    }
}
