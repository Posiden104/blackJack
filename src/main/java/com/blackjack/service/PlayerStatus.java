package com.blackjack.service;

/**
 * Created by posid on 11/21/2016.
 */
public enum PlayerStatus {
    // waiting on player bet
    WAITING_ON_BET,

    // waiting on another player for some reason
    WAITING_ON_OTHER_PLAYER,

    // waiting on the player to choose an action
    WAITING_ON_ACTION,

    // other error, call status again
    RECALL;
}
