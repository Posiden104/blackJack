package com.blackjack.controller;

import com.blackjack.service.BlackJackServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Vector;

/**
 * Created by posid on 11/20/2016
 */
@RestController
public class Controller {

    @Autowired
    public BlackJackServer bjs;

    /* Index endpoint */
    @RequestMapping("/")
    public String index(){
        return "BlackJackServer API index location.";
    }

    /* Test endpoint */
    @RequestMapping("/testing")
    public String testing(){
        return "This is a test endpoint";
    }

    /* Request update */
    @RequestMapping("/blackjack/v1.0/{playerId}/update")
    public String update(@PathVariable String playerId){
        return "" + playerId;
    }

    /* Place bets */
    @RequestMapping("/blackjack/v1.0/{playerId}/bet/{ammount}")
    public String bet(@PathVariable String playerId, @PathVariable int bet){
        return " " + playerId + bet;
    }

    /* used for new players */
    @RequestMapping("/blackjack/v1.0/join")
    public String addPlayer(){
        Vector<Integer> v;
        v = bjs.addPlayer();
        return v.toString();
    }

}
