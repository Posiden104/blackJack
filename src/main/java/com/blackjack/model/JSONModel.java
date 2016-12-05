package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JSONModel {

	// Private
	
	// Public
	public Table table;
	public int playerID;
	
	public JSONModel(Player p, Table t){
		table = t;
		playerID = p.getPlayerID();
	}
}
