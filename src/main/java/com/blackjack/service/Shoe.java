package com.blackjack.service;

/**
 * Created by posid on 11/21/2016.
 */
public class Shoe {
    private Card[] cards;
    private int nextCard = -1;

    public Shoe(int nDecks){
        cards = new Card[6*nDecks]
    }

    public Shoe(){
        // if no deck number is specified, play with 6 decks
        this(6);
    }

    public Card getCard(){
        nextCard ++;
        return cards[nextCard];

        //return new Card("Ace", "S");
    }

    private void shuffleDeck(){

    }
}
