package org.rpk.game.war.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import static java.util.stream.IntStream.range;

import org.rpk.game.war.Player;

/**
 * Card dealer.
 *
 * @author R. Paul Kennedy 
 */
public class Dealer {
	
	protected Deck deck;

	/** Creates a new dealer with a new deck of shuffled cards. */
	public Dealer() {
		this.deck = new Deck().shuffle();
	}
	
	/**
	 * @param players
	 * For each {@code player} in order, alternating between all players,
	 * removes a card from the deck and give it to the player until
	 * all cards are removed from the deck.
	 */
	public void deal(Player... players) {
		range(0, deck.getCards().size())
			.forEach(i -> players[i % players.length]
				.getHand()
				.add(deck.getCards().remove(0)));
	}
	
}
