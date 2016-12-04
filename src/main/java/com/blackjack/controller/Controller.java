package com.blackjack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.model.JSONModel;
import com.blackjack.service.BlackJackServer;
import com.blackjack.status.PlayerAction;

/**
 * Created by posid on 11/20/2016
 */
@RestController
public class Controller {

	@Autowired
	public BlackJackServer bjs;

	/* Index endpoint */
	@RequestMapping("/")
	public String index() {
		return "BlackJackServer API index location.";
	}

	/* Test endpoint */
	@RequestMapping("/testing")
	public String testing() {
		return "This is a test endpoint";
	}

	/* Request update */
	@RequestMapping("/blackjack/v1.0/{playerId}/{action}")
	public JSONModel update(@PathVariable int playerId, @PathVariable PlayerAction action) {
		return bjs.update(playerId, action, 0);
	}

	/* Place bets */
	@RequestMapping("/blackjack/v1.0/{playerId}/BET/{ammount}")
	public String bet(@PathVariable int playerId, @PathVariable int ammount) {
		//System.err.println("player " + playerId + " is betting $" + ammount);
		bjs.update(playerId, PlayerAction.BET, ammount);
		return " " + playerId + " $" + ammount;
	}

	/* used for new players */
	
	@RequestMapping("/blackjack/v1.0/join")
	public JSONModel addPlayer() {
		return bjs.addPlayer();
	}

}
