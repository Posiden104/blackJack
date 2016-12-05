package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JoinModel {
	
	// Public
	public int playerID;
	public Table table;
	
	public JoinModel(Player p, Table t){
		this.playerID = p.getPlayerID();
		this.table = t;
	}
}
