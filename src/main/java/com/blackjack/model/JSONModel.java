package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JoinModel {

	// Private
	
	// Public
	public Player player;
	public Table table;
	
	public JoinModel(Player p, Table t){
		player = p;
		table = t;
	}
}
