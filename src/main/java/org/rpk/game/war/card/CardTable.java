package org.rpk.game.war.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Card table upon which the game is played.
 * 
 * @author R. Paul Kennedy 
 */
public class CardTable {
	
	/** Cards in play for Player 1 */
	protected final Stack<Card> player1CardStack;
	
	/** Cards in play for Player 2 */
	protected final Stack<Card> player2CardStack;
	
	/** 
	 * Creates a new card table with places 
	 * for cards in play for both players.
	 */
	public CardTable() {
		this.player1CardStack = new Stack<>();
		this.player2CardStack = new Stack<>();
	}

	/** @return All played cards on table for Player 1 WITHOUT removing them. */
	public Stack<Card> getPlayer1CardStack() {
		return player1CardStack;
	}

	/** @return All played cards on table for Player 2 WITHOUT removing them. */
	public Stack<Card> getPlayer2CardStack() {
		return player2CardStack;
	}
	
	/** @return All played cards on table for Player 1, REMOVING them from the table. */
	public List<Card> takePlayer1CardStack() {
		final List<Card> player1CardsList = new ArrayList<>(getPlayer1CardStack());
		getPlayer1CardStack().clear();
		return player1CardsList;
	}

	/** @return All played cards on table for Player 2, REMOVING them from the table. */
	public List<Card> takePlayer2CardStack() {
		final List<Card> player2CardsList = new ArrayList<>(getPlayer2CardStack());
		getPlayer2CardStack().clear();
		return player2CardsList;
	}
	
	/** @return The currently played card for Player 1 WITHOUT removing it. */
	public Card peekPlayer1CardStack() {
		return player1CardStack.peek();
	}
	
	/** @return The currently played card for Player 2 WITHOUT removing it. */
	public Card peekPlayer2CardStack() {
		return player2CardStack.peek();
	}
	
}
