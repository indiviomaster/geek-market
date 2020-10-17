package com.geekbrains.geekmarket.controllers;


import com.geekbrains.geekmarket.entities.Greeting;
import com.geekbrains.geekmarket.entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ShopControllerWs {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000);
        return new Greeting(message.getName() + " добавлен в коризну!");
    }
}
