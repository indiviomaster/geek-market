package com.geekbrains.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiverApp {
    private final static String QUEUE_NAME = "RabbitTest";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false, null);
        System.out.println("waiting message");

        final DeliverCallback deliverCallback = (consumerTag, delivery) ->{
            String msg = new String(delivery.getBody(),"UTF-8");
            System.out.println("Receiver msg: "+ msg);
        };
        channel.basicConsume(QUEUE_NAME,true, deliverCallback, consumerTag->{});

    }
}
