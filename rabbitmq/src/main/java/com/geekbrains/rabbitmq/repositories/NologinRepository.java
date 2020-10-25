package com.geekbrains.rabbitmq.repositories;

import com.geekbrains.rabbitmq.entity.Nologin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NologinRepository extends JpaRepository<Nologin, Long> {

    List<Nologin>  findAll();

}
