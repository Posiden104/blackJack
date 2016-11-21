package com.blackjack.service;


import java.util.List;

/**
 * Created by posid on 11/20/2016.
 */
public class Player {
    int playerID;
    private List<Card> hand;
    PlayerStatus status = PlayerStatus.RECALL;

    public Player(int id){
        playerID = id;
    }

    public List<Card> getHand(){
        return hand;
    }
}
