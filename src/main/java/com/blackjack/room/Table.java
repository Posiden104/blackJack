package com.blackjack.room;

import java.util.ArrayList;

import com.blackjack.status.PlayerAction;
import com.blackjack.status.PlayerStatus;
import com.blackjack.status.TableStatus;

/**
 * Created by posid on 11/20/2016
 */
public class Table implements Runnable {

	// Private
	private final int N_SEATS = 6;

	private int tableID;
	private int n_players = 0;
	private int n_CheckedIn = 0;
	private int playerTurn = -1;
	private int dealerHandValue = 0;
	private int n_bet = 0;

	private boolean shuffleNext = false;
	private boolean isSoft = false;
	private boolean isClosed = false;
	private boolean running = true;

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

		status = TableStatus.WAITING_ON_BETS;
		playerTurn = -1;
	}

	/* Main loop for the table */
	public void run() {
		while (running) {
			if (n_players < 1) {
				if (!isClosed) {
					close();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
			} else {

				switch (status) {
				case DEALING:
					playerTurn = -1;
					startHand();
					break;

				case DEALER_TURN:
					playerTurn = -1;
					dealerPlays();
					break;

				case WAITING_ON_PLAYER:
					if (playerTurn < n_players) {
						players.get(playerTurn).setStatus(PlayerStatus.WAITING_ON_ACTION);
					} else {
						status = TableStatus.DEALER_TURN;
					}
					break;

				case WAITING_ON_CHECKIN:
					playerTurn = -1;
					break;

				case WAITING_ON_BETS:
					playerTurn = -1;
					break;

				default:
					playerTurn = -1;
					break;
				}

				if (n_players < 1) {
					isPlaying = false;
				}

				// wait 1/4 second to re-run loop
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
			}
		}
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
			}
		}

		calculateWin();
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

		System.err.println("updating player " + playerId);

		switch (pa) {
		case BET:
			if (status == TableStatus.WAITING_ON_BETS) {
				placeBet(p, bet);
			}
			break;

		case HIT:
			p.dealCard(deal());
			if (p.getHandValue() > 21) {
				p.setStatus(PlayerStatus.BUSTED);
			} else {
				p.setStatus(PlayerStatus.WAITING_ON_ACTION);
			}
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
	public void placeBet(Player p, int bet) {
		System.err.println("Player bet " + bet);
		p.setBet(bet);
		p.setStatus(PlayerStatus.WAITING_ON_OTHER_PLAYER);

		// check if all players have bets in
		if (!p.hasBet) {
			System.out.println("Player " + p.getPlayerID() + "'s status is " + p.getStatus());
			p.hasBet = true;
			n_bet++;
		}

		if (n_bet == n_players) {
			n_bet = 0;
			System.err.println("all players have bet");
			status = TableStatus.DEALING;
		}
	}

	/*
	 * Returns the Dealer's hand
	 */
	public ArrayList<Card> getDealerHand() {
		return dealerHand;
	}

	public int getDealerHandValue() {
		dealerHandValue = getValue(getDealerHand());
		return dealerHandValue;
	}

	/*
	 * Add players to the table
	 */
	public boolean addPlayer(Player p) {
		if (n_players < N_SEATS) {
			players.add(p);
			n_players++;
			System.out.println("Added player " + p.getPlayerID() + " to Table " + this.tableID);

			// Start the table if it isn't started already
			if (!isPlaying) {
				isPlaying = true;
				p.setStatus(PlayerStatus.WAITING_ON_BET);
			} else {
				if (status == TableStatus.WAITING_ON_BETS) {
					p.setStatus(PlayerStatus.WAITING_ON_BET);
				} else {
					p.setStatus(PlayerStatus.NEW_PLAYER);
				}
			}

			return true;
		}
		return false;
	}

	public void calculateWin() {
		int dValue = getDealerHandValue();
		if(dValue >21){
			dValue = -1;
		}
		for (Player p : players) {
			int value = p.getHandValue();
			if (value > 21) {
				p.setStatus(PlayerStatus.BUSTED);
			} else if (value == 21 && p.getHand().size() == 2) {
				p.setStatus(PlayerStatus.BLACKJACK);
			} else if (value > dValue) {
				p.setStatus(PlayerStatus.WON);
			} else if (value < dValue) {
				p.setStatus(PlayerStatus.LOST);
			} else {
				p.setStatus(PlayerStatus.NEED_READY);
			}
			p.updateMoney(p.getStatus());
		}

		status = TableStatus.WAITING_ON_CHECKIN;
	}

	public int getTableID() {
		return tableID;
	}

	/*
	 * Deal the first, starting hand to all players
	 */
	private void startHand() {
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
		playerTurn = 0;
	}

	/*
	 * Closes the table
	 */
	private void close() {
		shoe.shuffle();
		clearTable();
		isClosed = true;
	}

	/* Clears all cards from table */
	private void clearTable() {
		for (Player p : players) {
			p.clearHand();
			p.setBet(0);
			p.setStatus(PlayerStatus.WAITING_ON_BET);
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

	public void removePlayer(Player p) {
		players.remove(p);
		n_players--;
	}

	public TableStatus getStatus() {
		return status;
	}

	public void shutdown() {
		running = false;
	}

}
