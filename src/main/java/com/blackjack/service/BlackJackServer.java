package com.blackjack.service;


import java.util.ArrayList;
import java.util.List;

import com.blackjack.room.Table;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by posid on 11/20/2016
 */
@SpringBootApplication
@ComponentScan({ "com.blackjack"})
public class BlackJackServer {

    List<Table> tables;

    private BlackJackServer(){
        tables = new ArrayList<>();
       /*
        Table t = new Table(1);
        tables.add(t);

        for(Table tb : tables){
            tb.play();
        }
        */
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlackJackServer.class, args);

        BlackJackServer b = new BlackJackServer();
    }
}
