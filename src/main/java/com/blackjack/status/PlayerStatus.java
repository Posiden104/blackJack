package com.blackjack.status;

/**
 * Created by posid on 11/21/2016
 */
public enum PlayerStatus {
    // waiting on player bet
    WAITING_ON_BET,

    // waiting on another player for some reason
    WAITING_ON_OTHER_PLAYER,

    // waiting on the player to choose an action
    WAITING_ON_ACTION,
    
    // player just joined the table, wait until the hand is finished
    NEW_PLAYER,

    // other error, call status again
    RECALL,
    
    // player is not at any table
    NOT_SEATED
}
