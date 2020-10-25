package com.geekbrains.rabbitmq.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.geekbrains.rabbitmq.entity.Nologin;


public interface NologinService{

    void save(Nologin nologin) throws JsonProcessingException;

}
