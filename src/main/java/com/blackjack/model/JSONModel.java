package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JSONModel {

	// Private
	
	// Public
	public Table table;
	public int playerId;
	
	public JSONModel(Player p, Table t){
		table = t;
		playerId = p.getPlayerID();
	}
}
