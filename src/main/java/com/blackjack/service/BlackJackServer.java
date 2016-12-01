package com.blackjack.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

    private List<Table> tables;

    private long playerID = 0;

    private BlackJackServer(){
        tables = new ArrayList<>();
        Table t = new Table(0);
        tables.add(t);

        for (Table tb : tables) {
            tb.play();
        }
    }

    private void addTables(int numTables){
        for(int i = 0; i < numTables; i++) {
            Table t = new Table(tables.size());
            tables.add(t);
        }
    }

    public void addPl(){

 //       Player p = new Player(++playerID);
        Vector v = new Vector();
        for(Table t : tables){
//            if(t.addPlayer(p)){
            tables.size();
//            }
        }
        addTables(1);
 //       tables.get(tables.size() - 1).addPlayer(p);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlackJackServer.class, args);

        BlackJackServer b = new BlackJackServer();
        b.addTables(0);
    }
}
