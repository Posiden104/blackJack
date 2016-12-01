package com.blackjack.room;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by posid on 11/21/2016
 */
class Shoe {
    private Card[] cards;
    private int nextCard = -1;
    private int penetration = 0;

    /*
        Standard constructor
     */
    private Shoe(int nDecks){
        cards = new Card[6*nDecks];
        for(int i = 0; i < 6*nDecks; i++){
            cards[i] = genCard(i);
        }
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
        if(nextCard == penetration){
            return new Card("Cut", "Card");
        }
        return cards[nextCard];
        //return new Card("Ace", "S");
    }

    Card drawAfterCutCard(){
        return cards[nextCard];
    }

    /* Shuffle the deck to randomize the next shoe */
    void shuffle(){
        Random rnd = ThreadLocalRandom.current();
        int numTimes = rnd.nextInt(5);
        int numCards = cards.length;
        for (int i = 0; i < numTimes; i++){
            int index = rnd.nextInt(numCards);
            // Simple swap
            Card a = cards[index];
            cards[index] = cards[i];
            cards[i] = a;
        }

        double lowerBound = cards.length * 1.25;
        double upperBound = cards.length *1.75;
        int range = (int)(upperBound - lowerBound);
        penetration = rnd.nextInt(range + 1) + 65;
        nextCard = -1;
    }

    private Card genCard(int cardNum){
        String name = "-1";
        String suit = "Q";

        switch(cardNum % 13){
            case 0:
                name = "Ace";
                break;
            case 1:
                name = "2";
                break;
            case 2:
                name = "3";
                break;
            case 3:
                name = "4";
                break;
            case 4:
                name = "5";
                break;
            case 5:
                name = "6";
                break;
            case 6:
                name = "7";
                break;
            case 7:
                name = "8";
                break;
            case 8:
                name = "9";
                break;
            case 9:
                name = "10";
                break;
            case 10:
                name = "Jack";
                break;
            case 11:
                name = "Queen";
                break;
            case 12:
                name = "King";
                break;
        }

        int sNum = cardNum % 52;

        if(sNum <= 12) {
            suit = "S";
        } else if( sNum >= 13 && sNum <= 25) {
            suit = "C";
        } else if(sNum >= 26 && sNum <= 38) {
            suit = "H";
        } else if(sNum >= 39 && sNum <= 51) {
            suit = "D";
        }

        if(suit.equals("Q") || name.equals("-1")){
            System.err.println("Card gen error");
            return null;
        }

        return new Card(name, suit);
    }
}
