package com.blackjack.room;

import com.blackjack.status.PlayerAction;
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
	private int playerTurn = 0;

	private boolean shuffleNext = false;
	private boolean isSoft = false;

	private Shoe shoe;
	private TableStatus status;
	private ArrayList<Player> players;
	private ArrayList<Card> dealerHand;
	private Card hiddenCard;

	// Public
	public boolean isPlaying = false;

	public Table(int tableID) {
		this.tableID = tableID;
		shoe = new Shoe();
		shoe.shuffle();
		players = new ArrayList<>();
		dealerHand = new ArrayList<>();

		status = TableStatus.WAITING_ON_PLAYER;
		playerTurn = 1;
	}

	public void play() {
		while (isPlaying) {

			switch (status) {
			case DEALING:
				playerTurn = 0;
				dealCards();
				break;

			case DEALER_TURN:
				playerTurn = 0;
				dealerPlays();
				break;

			case WAITING_ON_PLAYER:
				break;
			// case WAITING_ON_2:
			// playerTurn = 2;
			// break;
			// case WAITING_ON_3:
			// playerTurn = 3;
			// break;
			// case WAITING_ON_4:
			// playerTurn = 4;
			// break;
			// case WAITING_ON_5:
			// playerTurn = 5;
			// break;
			// case WAITING_ON_6:
			// playerTurn = 6;
			// break;
			case WAITING_ON_CHECKIN:
				playerTurn = 0;
				break;
			default:
				playerTurn = 0;
				break;
			}

			if (n_players < 1) {
				isPlaying = false;
			}
		}

		close();
	}

	/* Returns the value of a given hand list */
	static int getValue(ArrayList<Card> hand) {
		int value = 0;
		int nAces = 0;

		for (Card c : hand) {
			int cv = c.getValue();
			if (cv > 0) {
				value += cv;
			} else if (cv == -1) {
				nAces++;
			}
		}

		for (int i = 0; i < nAces; i++) {
			if (value > 10) {
				value += 1;
			} else {
				value += 11;
			}
		}

		return value;
	}

	/* Dealer deals to himself */
	private void dealerPlays() {
		dealerHand.add(hiddenCard);

		boolean flag = false;

		while (!flag) {
			int val = getValue(dealerHand);
			if (val < 17) {
				dealerHand.add(deal());
				if (dealerHand.get(dealerHand.size() - 1).getValue() == -1) {
					isSoft = true;
				}
			} else if (val == 17 && isSoft) {
				dealerHand.add(deal());
			} else {
				// stand
				flag = true;
				// TODO: win / loss logic
			}
		}

		status = TableStatus.WAITING_ON_CHECKIN;
	}

	private Card deal() {
		Card c = shoe.drawCard();
		if (c.getName().equals("CutCard")) {
			shuffleNext = true;
			c = shoe.drawAfterCutCard();
		}
		return c;
	}

	public void update(int playerId, PlayerAction pa, int bet) {
		Player p = getPlayer(playerId);

		switch (pa) {
		case BET:
			placeBet(p, bet);
			break;

		case HIT:
			p.dealCard(deal());
			p.setStatus(PlayerStatus.WAITING_ON_ACTION);
			break;

		case STAND:
			playerTurn++;
			p.setStatus(PlayerStatus.WAITING_ON_OTHER_PLAYER);
			break;

		case UPDATE:
			break;

		case READY:
			checkIn(p);
			break;

		default:
			break;
		}
	}

	/* Players check in here */
	public void checkIn(Player p) {
		if (status == TableStatus.WAITING_ON_CHECKIN) {
			if (!p.checkedIn) {
				System.out.println("Player " + p.getPlayerID() + "'s status is " + p.getStatus());
				p.checkedIn = true;
				n_CheckedIn++;
			}

			if (n_CheckedIn == n_players) {
				n_CheckedIn = 0;
				clearTable();
				status = TableStatus.WAITING_ON_BETS;
			}
		}
	}

	/* Places bet for player and checks if any players still need to bet */
	public void placeBet(Player p, int bet){
		p.setBet(bet);
		p.setStatus(PlayerStatus.WAITING_ON_OTHER_PLAYER);
		
	}
	
	/* Sends update to player */
	public void playerUpdate(int playerId, PlayerAction pa, int bet) {
		Player p = getPlayer(playerId);
		if (playerTurn == playerId) {
			// TODO: this method
			p.getBet();
		} else {

		}
	}

	/*
	 * Returns the Dealer's hand
	 */
	public List<Card> getDealerHand() {
		return dealerHand;
	}

	/*
	 * Add players to the table
	 */
	public boolean addPlayer(Player p) {
		if (n_players < N_SEATS) {
			players.add(p);
			System.out.println("Added player " + p.getPlayerID() + " to Table " + this.tableID);

			// Start the table if it isn't started already
			if (!isPlaying) {
				isPlaying = true;
				p.setStatus(PlayerStatus.WAITING_ON_BET);
				play();
			} else {
				p.setStatus(PlayerStatus.NEW_PLAYER);
			}

			return true;
		}
		return false;
	}

	public int getTableID() {
		return tableID;
	}

	/*
	 * Deal the first, starting hand to all players
	 */
	private void dealCards() {
		if (shuffleNext) {
			shoe.shuffle();
			shuffleNext = false;
		}

		dealerHand.clear();

		for (Player p : players) {
			p.clearHand();
			p.checkedIn = false;
		}

		n_CheckedIn = 0;

		for (int i = 0; i < 2; i++) {
			for (Player p : players) {
				if (!(p.getStatus() == PlayerStatus.NEW_PLAYER)) {
					p.dealCard(shoe.drawCard());
				}
			}

			if (i == 0) {
				dealerHand.add(shoe.drawCard());
			} else {
				hiddenCard = shoe.drawCard();
			}
		}

		status = TableStatus.WAITING_ON_PLAYER;
		playerTurn = 1;
	}

	/*
	 * Closes the table
	 */
	private void close() {
		shoe.shuffle();
		clearTable();
	}

	/* Clears all cards from table */
	private void clearTable() {
		for (Player p : players) {
			p.clearHand();
		}
		dealerHand.clear();
	}

	/*
	 * Returns the player if the player is at the table otherwise returns a
	 * player with playerId -1
	 */
	public Player getPlayer(int playerId) {
		for (Player p : players) {
			if (p.getPlayerID() == playerId) {
				return p;
			}
		}
		return new Player(-1);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public boolean isShuffleNext() {
		return shuffleNext;
	}

}
