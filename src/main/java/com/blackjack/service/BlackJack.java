package com.blackjack.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by posid on 11/20/2016.
 */
@SpringBootApplication
@ComponentScan({ "com.blackjack"})
public class BlackJack {

    List<Table> tables;

    public BlackJack(){
        tables = new ArrayList<>();
        Table t = new Table(1);
        tables.add(t);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlackJack.class, args);

        BlackJack b = new BlackJack();
    }
}
