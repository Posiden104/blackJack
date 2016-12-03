package com.blackjack.room;


import com.blackjack.status.PlayerStatus;

import java.util.ArrayList;

/**
 * Created by posid on 11/20/2016
 */
public class Player {
    private int playerID;
    private ArrayList<Card> hand;
    private int handValue = 0;
    public boolean checkedIn = false;
    PlayerStatus status = PlayerStatus.RECALL;

    public Player(int id){
        playerID = id;
        hand = new ArrayList<>();
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

    void clearHand() {
        hand.clear();
    }
}
