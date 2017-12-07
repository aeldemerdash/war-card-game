package org.rpk.game.war.card;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.rpk.game.war.card.Deck;
import org.rpk.game.war.card.Rank;
import org.rpk.game.war.card.Suit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class DeckTest {

	Deck deck;
	
	@BeforeMethod
	public void beforeMethod() {
		deck = new Deck();
	}

	/** There should be 52 unique cards */
	public void getCardsUnique52() {
		assertEquals(new HashSet<>(deck.getCards()).size(), 52);
	}
	
	/** Cards should be the same before shuffling */
	public void beforeShuffle() {
		assertEquals(new Deck().getCards(), deck.getCards());
	}
	
	/** Cards should be different after shuffling */
	public void shuffle() {
		assertNotEquals(new Deck().shuffle().getCards(), deck.getCards());
	}

	/** String should be a CSV list of 52 unique symbols cards matching valid ranks and suits */
	public void toStringTest() {
		// Set of all rank symbols
		final Set<Character> rankSymbols =
			unmodifiableSet(
				Arrays.asList(Rank.values())
					.parallelStream()
					.map(rank -> rank.symbol())
					.collect(toSet()));
			
		// Set of all suit symbols
		final Set<Character> suitSymbols = 
			unmodifiableSet(
				Arrays.asList(Suit.values())
					.parallelStream()
					.map(suit -> suit.symbol())
					.collect(toSet()));
		
		// Set of all card symbols in the string
		Set<String> uniqueSymbols = new HashSet<>(Arrays.asList(deck.toString().split(",")));
		
		// Make sure there are 52 card symbols
		assertEquals(uniqueSymbols.size(), 52);
		
		// Make sure each card symbol is a String of rank symbol + suit symbol
		uniqueSymbols
			.parallelStream()
			.map(symbol -> symbol.toCharArray())
			.forEach(symbolArray -> {
				assertTrue(rankSymbols.contains(symbolArray[0]));
				assertTrue(suitSymbols.contains(symbolArray[1]));
			});
	}
}
