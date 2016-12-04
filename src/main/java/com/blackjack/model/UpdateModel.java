package com.blackjack.model;

import java.util.ArrayList;

import com.blackjack.room.Player;

public class UpdateModel {

	// Private
	private ArrayList<Player> players;
	
	
	// Public
	
	public UpdateModel(ArrayList<Player>pList){
		players = pList;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
}
