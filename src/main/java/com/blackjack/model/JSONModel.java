package com.blackjack.model;

import com.blackjack.room.Player;
import com.blackjack.room.Table;

public class JSONModel {

	// Private
	
	// Public
	public Player player;
	public Table table;
	
	public JSONModel(Player p, Table t){
		player = p;
		table = t;
	}
}
