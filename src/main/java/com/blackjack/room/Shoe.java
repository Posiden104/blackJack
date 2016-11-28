package com.blackjack.room;

/**
 * Created by posid on 11/21/2016
 */
class Shoe {
    private Card[] cards;
    private int nextCard = -1;

    /*
        Standard constructor
     */
    private Shoe(int nDecks){
        cards = new Card[6*nDecks];

        shuffle();
    }

    /*
       Generic constructor. Uses 6 decks
     */
    Shoe(){
        this(6);
    }

    Card drawCard(){
        nextCard ++;
        return cards[nextCard];
        //return new Card("Ace", "S");
    }

    /* Shuffle the deck to randomize the next hand */
    void shuffle(){

    }
}
