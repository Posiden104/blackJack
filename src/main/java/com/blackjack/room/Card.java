package com.blackjack.room;

/**
 * Created by posid on 11/20/2016
 */
class Card {

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    /*
    public void setName(String name) {
    this.name = name;
    }
    */

    public int getValue(){
        return this.value;
    }

    Card(String name, String suit){
        this.name = name;
        this.name += suit;
        switch(name){
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
                this.value = Integer.parseInt(name);
                break;
        }
    }
}
