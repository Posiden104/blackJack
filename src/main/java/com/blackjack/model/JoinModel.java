package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JoinModel {
	
	// Public
	public int playerId;
	public Table table;
	
	public JoinModel(Player p, Table t){
		this.playerId = p.getPlayerID();
		this.table = t;
	}
}
