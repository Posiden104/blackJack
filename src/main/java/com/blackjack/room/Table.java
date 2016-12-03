package com.blackjack.room;

import com.blackjack.status.PlayerStatus;
import com.blackjack.status.TableStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by posid on 11/20/2016
 */
public class Table {

	// Private
    private final int N_SEATS = 6;

    private int tableID;
    private int n_players = 0;
    private int n_CheckedIn = 0;

    private boolean shuffleNext = false;
    private boolean isSoft = false;
    

    private Shoe shoe;
    private TableStatus status;
    private List<Player> players;
    private ArrayList<Card> dealerHand;
    private Card hiddenCard;

    // Public
    public boolean isPlaying = false;
    

    public Table(int tableID){
        this.tableID = tableID;
        shoe = new Shoe();
        shoe.shuffle();
        players = new ArrayList<>();
        dealerHand = new ArrayList<>();

        status  = TableStatus.DEALING;
    }

    public void play(){
        while(isPlaying) {
        	
            switch (status) {
                case DEALING:
                    dealCards();
                    break;

                case DEALER_TURN:
                    dealerPlays();
                    break;

                case WAITING_ON_1:
                    break;
                case WAITING_ON_2:
                    break;
                case WAITING_ON_3:
                    break;
                case WAITING_ON_4:
                    break;
                case WAITING_ON_5:
                    break;
                case WAITING_ON_6:
                    break;
                case WAITING_ON_CHECKIN:
                    break;
                default:
                    break;
            }
            
            if(n_players < 1) {
            	isPlaying = false;
            }
        }

        close();
    }

    /* Returns the value of a given hand list */
    static int getValue(ArrayList<Card> hand){
        int value = 0;
        int nAces = 0;

        for(Card c : hand){
            int cv = c.getValue();
            if(cv > 0){
                value += cv;
            } else if(cv == -1){
                nAces++;
            }
        }

        for(int i = 0; i < nAces; i++){
            if(value > 10){
                value += 1;
            } else {
                value += 11;
            }
        }

        return value;
    }

    /* Dealer deals to himself */
    private void dealerPlays(){
        dealerHand.add(hiddenCard);

        boolean flag = false;

        while(!flag){
            int val = getValue(dealerHand);
            if(val < 17) {
                dealerHand.add(deal());
                if(dealerHand.get(dealerHand.size() - 1).getValue() == -1){
                    isSoft = true;
                }
            } else if(val == 17 && isSoft){
                dealerHand.add(deal());
            } else {
                // stand
            	// TODO: win / loss logic
            }
        }

        status = TableStatus.WAITING_ON_CHECKIN;
    }

    private Card deal(){
        Card c = shoe.drawCard();
        if (c.getName().equals("CutCard")) {
            shuffleNext = true;
            c = shoe.drawAfterCutCard();
        }
        return c;
    }

    /* Players check in here */
    public void checkIn(int playerId){
        if(status == TableStatus.WAITING_ON_CHECKIN || status == TableStatus.WAITING_ON_BETS){
            for(Player p : players){
                if(p.getPlayerID() == playerId){
                    p.checkedIn = true;
                    n_CheckedIn++;
                }
            }
            if(n_CheckedIn == n_players){
                n_CheckedIn = 0;
                if(status == TableStatus.WAITING_ON_CHECKIN){
                    status = TableStatus.WAITING_ON_BETS;
                } else {
                    status = TableStatus.DEALING;
                }
            }
        } else {
            // TODO: switch player, look at their status
        }
    }

    /*
        Returns the Dealer's hand
     */
    public List<Card> getDealerHand(){
        return dealerHand;
    }

    /*
        Add players to the table
     */
    public boolean addPlayer(Player p){
        if(n_players < N_SEATS) {
            players.add(p);
            System.out.println("Added player to Table " + this.tableID);
            
            // Start the table if it isn't started already
            if(!isPlaying){
            	isPlaying = true;
            	p.setStatus(PlayerStatus.WAITING_ON_BET);
            	play();
            }
            
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
    private void dealCards(){
        if(shuffleNext){
        	shoe.shuffle();
        	shuffleNext = false;
        }
        
        dealerHand.clear();

        for(Player p : players){
            p.clearHand();
            p.checkedIn = false;
        }

        n_CheckedIn = 0;

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
    }

    /*
        Closes the table
     */
    private void close(){
        shoe.shuffle();
        dealerHand.clear();
    }

    /*
    public boolean isFull(){
        return full;
    }
    */
}
