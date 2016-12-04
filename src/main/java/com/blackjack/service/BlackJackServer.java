package com.blackjack.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.blackjack.model.JSONModel;
import com.blackjack.room.Player;
import com.blackjack.room.Table;
import com.blackjack.status.PlayerAction;
import com.blackjack.status.PlayerStatus;

/**
 * Created by posid on 11/20/2016
 */
@SpringBootApplication
@ComponentScan({ "com.blackjack"})
public class BlackJackServer {

    private List<Table> tables;

    private int playerID = 0;
 
    
    @Autowired
    public BlackJackServer bjs;

    public BlackJackServer(){
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

    public JSONModel update(int playerId, PlayerAction pa, int bet){
    	Player p;
    	for(Table t : tables){
    		p = t.getPlayer(playerId);
    		if(p.getPlayerID() == playerId){
    			t.playerUpdate(playerId, pa, bet);
    			JSONModel jm = new JSONModel(p, t);
    			return jm;
    		}
    	}
    	p = new Player(-1);
    	p.setStatus(PlayerStatus.NOT_SEATED);
    	return new JSONModel(p, new Table(-1));
    }
    
    public JSONModel addPlayer(){
        Player p = new Player(++playerID);
        JSONModel jm;
        for(Table t : tables){
            if(t.addPlayer(p)){
            	 jm = new JSONModel(p, t);
            	 return jm;
            }
        }
        addTables(1);
        tables.get(tables.size() - 1).addPlayer(p);
        jm = new JSONModel(p, tables.get(tables.size() -1));
        return jm;
    }

    @PostConstruct
    public void init(){
        bjs = new BlackJackServer();
        bjs.addTables(0);
    }
    
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(BlackJackServer.class, args);
    }
}
