package org.rpk.game.war.card;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.IntStream;

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
		Queue<Player> playerQueue = new ArrayDeque<>(Arrays.asList(players));
		IntStream.range(0, deck.getCards().size()).forEach(i -> {
			Player player = playerQueue.remove();
			player.getHand().add(deck.getCards().remove(0));
			playerQueue.add(player);			
		});
	}
	
}
