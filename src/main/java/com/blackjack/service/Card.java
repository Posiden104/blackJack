package com.blackjack.service;

/**
 * Created by posid on 11/20/2016.
 */
public class Card {

    private String card;
    private int value;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getValue(){
        return this.value;
    }

    public Card(String card, String suit){
        this.card = card;
        this.card += suit;
        switch(card){
            case "A":
                this.value = -1;
                break;
            case "Jack":
                this.value = 10;
                break;
            case "Queen":
                this.value = 10;
                break;
            case "King":
                this.value = 10;
                break;
            default:
                this.value = Integer.parseInt(card);
                break;
        }
    }
}
