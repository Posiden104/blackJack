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
import com.blackjack.model.JoinModel;
import com.blackjack.room.Player;
import com.blackjack.room.Table;
import com.blackjack.status.PlayerAction;
import com.blackjack.status.PlayerStatus;

/**
 * Created by posid on 11/20/2016
 */
@SpringBootApplication
@ComponentScan({ "com.blackjack" })
public class BlackJackServer {

	private List<Table> tables;
	private ArrayList<Thread> tableThreads;

	private int playerId = 0;

	@Autowired
	public BlackJackServer bjs;

	public BlackJackServer() {
		tables = new ArrayList<>();
		tableThreads = new ArrayList<>();
		Table t = new Table(0);
		tables.add(t);
		Thread th = new Thread(t);
		tableThreads.add(th);
		th.start();
	}

	private Table addTable() {
		Table t = new Table(tables.size());
		tables.add(t);
		Thread th = new Thread(t);
		tableThreads.add(th);
		th.start();
		return t;
	}

	public JSONModel update(int playerId, PlayerAction pa, int bet) {
		Player p;
		for (Table t : tables) {
			p = t.getPlayer(playerId);
			if (p.getPlayerID() == playerId) {
				t.update(playerId, pa, bet);
				JSONModel jm = new JSONModel(p, t);
				return jm;
			}
		}
		p = new Player(-1);
		p.setStatus(PlayerStatus.NOT_SEATED);
		return new JSONModel(p, new Table(-1));
	}

	public JoinModel addPlayer() {
		Player p = new Player(++playerId);
		JoinModel jm;
		for (Table t : tables) {
			if (t.addPlayer(p)) {
				jm = new JoinModel(p, t);
				return jm;
			}
		}
		Table t = addTable();
		t.addPlayer(p);
		jm = new JoinModel(p, t);
		return jm;
	}

	public void shutdown() {
		System.err.println("shutting down server");
		for (Table t : tables) {
			t.shutdown();
		}
	}

	public void removePlayer(int playerId) {
		for (Table t : tables) {
			Player p = t.getPlayer(playerId);
			if (p.getPlayerID() == playerId) {
				t.removePlayer(p);
				return;
			}
		}

	}

	public void reset() {
		for (Table t : tables) {
			t.shutdown();
		}
		try {
			for (Thread th : tableThreads) {
				th.join();
			}
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
		tables.clear();
		tableThreads.clear();
		playerId = 0;
	}

	@PostConstruct
	public void init() {
		bjs = new BlackJackServer();
		bjs.addTable();
	}

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BlackJackServer.class, args);
	}

}
