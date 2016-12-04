package com.blackjack.room;


import com.blackjack.status.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by posid on 11/20/2016
 */
public class Player {
    // Private
	private int playerID;
	private int handValue = 0;
	private int currentBet = 0;
	private int money = 0;

	private ArrayList<Card> hand;
    private PlayerStatus status = PlayerStatus.NEW_PLAYER;

    // Public
    public boolean checkedIn = false;

    public Player(int id){
        playerID = id;
        hand = new ArrayList<>();
        money = 1000;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

    void dealCard(Card c){
        hand.add(c);
        handValue = Table.getValue(hand);
    }

    public int getPlayerID(){
        return playerID;
    }

    public int getHandValue(){
        return handValue;
    }

    public void clearHand() {
        hand.clear();
    }
    
    public void setStatus(PlayerStatus s){
    	status = s;
    }
    
    public PlayerStatus getStatus(){
    	return status;
    }
    
    public int getBet(){
    	return currentBet;
    }
    
    public void setBet(int bet){
    	currentBet = bet;
    }

	public int getMoney() {
		return money;
	}

	public void updateMoney(PlayerStatus ps) {
		switch(ps){
		case WON:
			money += currentBet;
			break;
		
		case BUSTED:
		case LOST:
			money -= currentBet;
			break;
			
		case BLACKJACK:
			money += 1.5 * currentBet;
			break;
			
		default:
			break;
			
		}
	}
    
}
