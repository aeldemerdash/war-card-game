package org.rpk.game.war.card;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
		return String.join(",", getCards()
			.stream()
			.map(c -> c.toString())
			.collect(toList()));
	}

	protected List<Card> createCards() {
		return Stream.of(Suit.values())
			.flatMap(suit -> Stream.of(Rank.values())
				.map(rank -> new Card(rank, suit)))
			.collect(toList());
	}

}
