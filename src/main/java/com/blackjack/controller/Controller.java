package com.blackjack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blackjack.model.JSONModel;
import com.blackjack.model.JoinModel;
import com.blackjack.service.BlackJackServer;
import com.blackjack.status.PlayerAction;

/**
 * Created by posid on 11/20/2016
 */
@RestController
public class Controller {

	@Autowired
	public BlackJackServer bjs;

	/* Index endpoint
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
	*/
	
	/* Test endpoint */
	@RequestMapping("/testing")
	public String testing() {
		return "This is a test endpoint";
	}

	/* Request update */
	@CrossOrigin
	@RequestMapping("/blackjack/v1.0/{playerId}/{action}")
	public JSONModel update(@PathVariable int playerId, @PathVariable PlayerAction action) {
		//System.out.println("player action");
		return bjs.update(playerId, action, 0);
	}

	/* Place bets */
	@CrossOrigin
	@RequestMapping("/blackjack/v1.0/{playerId}/BET/{ammount}")
	public JSONModel bet(@PathVariable int playerId, @PathVariable int ammount) {
		//System.err.println("player " + playerId + " is betting $" + ammount);
		return bjs.update(playerId, PlayerAction.BET, ammount);
	}

	/* used for new players */
	@CrossOrigin
	@RequestMapping("/blackjack/v1.0/join")
	public JoinModel addPlayer() {
		return bjs.addPlayer();
	}
	
	/* player leaves table */
	@CrossOrigin
	@RequestMapping("/blackjack/v1.0/{playerId}/leave")
	public void leave(@PathVariable int playerId){
		System.out.println("player leave");
		bjs.removePlayer(playerId);
	}

	/* Resets the server */
	@CrossOrigin
	@RequestMapping("/blackjack/v1.0/reset")
	public String reset(){
		bjs.reset();
		return "server reset, all tables deleted";
	}
	
//	/* shuts down the server */
//	@RequestMapping("/shutdown")
//	public String shutdown(){
//		bjs.shutdown();
//		return "server shutdown.";
//	}
	
}
