package org.rpk.game.war.card;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Card table upon which the game is played.
 * 
 * @author R. Paul Kennedy 
 */
public class CardTable {
	
	/** Cards in play for Player 1 */
	protected final Stack<Card> player1Cards;
	
	/** Cards in play for Player 2 */
	protected final Stack<Card> player2Cards;
	
	/** 
	 * Creates a new card table with places 
	 * for cards in play for both players.
	 */
	public CardTable() {
		player1Cards = new Stack<>();
		player2Cards = new Stack<>();
	}

	/** @return All played cards on table for Player 1 WITHOUT removing them. */
	public Stack<Card> getPlayer1Cards() {
		return player1Cards;
	}

	/** @return All played cards on table for Player 2 WITHOUT removing them. */
	public Stack<Card> getPlayer2Cards() {
		return player2Cards;
	}
	
	/** @return All played cards on table for Player 1, REMOVING them from the table. */
	public List<Card> takePlayer1Cards() {
		final List<Card> player1CardsList = new ArrayList<>(getPlayer1Cards());
		getPlayer1Cards().clear();
		return player1CardsList;
	}

	/** @return All played cards on table for Player 2, REMOVING them from the table. */
	public List<Card> takePlayer2Cards() {
		final List<Card> player2CardsList = new ArrayList<>(getPlayer2Cards());
		getPlayer2Cards().clear();
		return player2CardsList;
	}
	
	/** @return The currently played card for Player 1 WITHOUT removing it. */
	public Card peekPlayer1Card() {
		return player1Cards.peek();
	}
	
	/** @return The currently played card for Player 2 WITHOUT removing it. */
	public Card peekPlayer2Card() {
		return player2Cards.peek();
	}
	
}
