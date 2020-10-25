package com.geekbrains.rabbitmq.entity;

import javax.persistence.*;

@Entity
@Table(name = "nologin")
public class Nologin {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String loginName;


    public Nologin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Nologin(String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "nologin{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                '}';
    }
}
