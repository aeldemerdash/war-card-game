package org.rpk.game.war;


import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.rpk.game.war.card.Card;
import org.rpk.game.war.card.CardTable;

/**
 * Player of the game.
 *
 * @author R. Paul Kennedy 
 */
public class Player {
	
	public static final String 
		Player1Name = "Player"
		, Player2Name = "Computer";

	/** Cards held by player. */
	protected final Queue<Card> hand;

	/** Proper name of player. */
	protected final String name;

	public Player(String name) {
		hand = new LinkedList<>();
		this.name = name;
	}

	public Queue<Card> getHand() {
		return hand;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return getHand().size();
	}

	/** Put a card on the table for normal play */
	public void playCard(Stack<Card> tableCards) {
		tableCards.push(getHand().remove());
	}

	/** Put 3 cars on the table for a war */
	public void playThreeCards(Stack<Card> tableCards) {
		IntStream
		.range(0, getHand().size() < 3 ? getHand().size() : 3)
		.forEachOrdered(i -> playCard(tableCards));
	}

	/**
	 * Takes all the cards from the card table, shuffles them, and puts them 
	 * in the bottom of the player's hand.
	 * @param table The card table
	 * @param random  
	 */
	public void collectCards(CardTable table) {
		// Take cards
		final List<Card> wonCards = 
			Stream.of(table.takePlayer1CardStack(), table.takePlayer2CardStack())
				.parallel()
				.flatMap(x -> x.parallelStream())
				.collect(toList());
		
		// Shuffle cards
		Collections.shuffle(wonCards);
		
		// Add cards to hand
		getHand().addAll(wonCards);
	}
	
}
