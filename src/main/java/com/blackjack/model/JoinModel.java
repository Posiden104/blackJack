package com.blackjack.model;

import com.blackjack.room.Table;

public class JoinModel {
	
	// Public
	public int playerId;
	public Table t;
	
	public JoinModel(Table t, int playerId){
		this.playerId = playerId;
		this.t = t;
	}
}
