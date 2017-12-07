package org.rpk.game.war.card;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Deck of cards.
 * 
 * @author R. Paul Kennedy 
 */
public class Deck {

	protected final List<Card> cards;

	public Deck() {
		this.cards = createCards();
	}

	public Deck shuffle() {
		Collections.shuffle(cards);
		return this;
	}

	public List<Card> getCards() {
		return this.cards;
	}

	public String toString() {
		return String
			.join(",", getCards()
				.stream()
				.map(c -> c.toString())
				.collect(toList()));
	}
	
	/** Creates a card for each suit for each rank. */
	protected List<Card> createCards() {
		List<Suit> suits = Arrays.asList(Suit.values());
		List<Rank> ranks = Arrays.asList(Rank.values());
		return suits.stream().map(suit -> 
			ranks.stream().map(rank -> 
				new Card(rank, suit)
			).collect(toList())
		).flatMap(cards -> cards.stream()).collect(toList());
	}

}
