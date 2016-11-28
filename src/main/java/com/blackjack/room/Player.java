package com.blackjack.room;


import com.blackjack.status.PlayerStatus;

import java.util.List;

/**
 * Created by posid on 11/20/2016
 */
class Player {
    private int playerID;
    private List<Card> hand;
    PlayerStatus status = PlayerStatus.RECALL;

    public Player(int id){
        playerID = id;
    }

    public List<Card> getHand(){
        return hand;
    }

    void dealCard(Card c){
        hand.add(c);
    }
}
