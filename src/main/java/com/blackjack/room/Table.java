package com.blackjack.room;

import com.blackjack.status.TableStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by posid on 11/20/2016
 */
public class Table {
    private int tableID;
    public final int N_SEATS = 6;
    private int n_players = 0;
    private boolean full = false;
    private Shoe shoe;
    private TableStatus status;
    private List<Player> players;

    public boolean showDealerHand = false;
    private List<Card> dealerHand;
    private Card hiddenCard;


    public Table(int tableID){
        this.tableID = tableID;
        shoe = new Shoe();
        shoe.shuffle();
        players = new ArrayList<>();
        dealerHand = new ArrayList<>();

        status  = TableStatus.DEALING;
    }

    public void play(){
        while(n_players > 0) {

            switch (status) {
                case DEALING:
                    for (int i = 0; i < 2; i++) {
                        for (Player p : players) {
                            p.dealCard(shoe.drawCard());
                        }

                        if (i == 0) {
                            dealerHand.add(shoe.drawCard());
                        } else {
                            hiddenCard = shoe.drawCard();
                        }
                    }
                    break;

                case DEALER_TURN:

                    break;

                default:
                    break;
            }
        }

        close();
    }

    public List<Card> getDealerHand(){
        return dealerHand;
    }

    /*
        Add players to the table
     */
    public boolean addPlayer(Player p){
        if(n_players < 6) {
            players.add(p);
            return true;
        }
        return false;
    }

    public int getTableID(){
        return tableID;
    }

    /*
     * Deal the first, starting hand to all players
     */
    public void dealCards(){

    }

    /*
        Closes the table
     */
    private void close(){

    }

    public boolean isFull(){
        return full;
    }

}
