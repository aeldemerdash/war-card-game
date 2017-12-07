package org.rpk.game.war.card;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.rpk.game.war.card.Rank.*;
import static org.rpk.game.war.card.Suit.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;

import org.rpk.game.war.card.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class CardTest {
	
	Card card1
		, card1Dup
		, card2
		, card2Eq
		, card3
		, card4;
	
	@BeforeMethod
	public void beforeMethod() {
		card1 = new Card(Ace, Clubs);
		card1Dup = new Card(Ace, Clubs);
		card2 = new Card(Ten, Diamonds);
		card2Eq = new Card(Ten, Clubs);
		card3 = new Card(Deuce, Spades);
		card4 = new Card(Four, Hearts);
	}

	public void compareToGt() {
		assertTrue(card1.compareTo(card2) > 0);
	}

	public void compareToLt() {
		assertTrue(card2.compareTo(card1) < 0);
	}
	
	public void compareToEqSameCard() {
		assertEquals(card1.compareTo(card1), 0);
	}
	
	public void compareToEqDifferentCardsSameRank() {
		assertEquals(card2.compareTo(card2Eq), 0);
	}

	public void getRank() {
		assertNotNull(card1.getRank());
	}

	public void getSuit() {
		assertNotNull(card1.getSuit());
	}

	/** Symbol should be the combined symbol of rank + suit. */
	public void getSymbol() {
		assertEquals(
			card1.getSymbol()
			, String.format("%s%s", Ace.symbol(), Clubs.symbol()));
	}

	/** Value should be the ordinal of the rank. */
	public void getValue() {
		Arrays.asList(card1, card2, card3, card4)
			.parallelStream()
			.forEach(card -> assertEquals(card.getValue(), card.getRank().ordinal()));
	}

	/** toString should return the symbol */
	public void toStringTest() {
		Arrays.asList(card1, card2, card3, card4)
			.parallelStream()
			.forEach(card -> assertEquals(card.toString(), card.getSymbol()));
	}
	
	public void equalsSameInstance() {
		assertTrue(card1.equals(card1));
	}
	
	public void equalsNotInstance() {
		assertFalse(card1.equals(Integer.valueOf(100)));
	}
	
	public void equalsDuplicate() {
		assertTrue(card1.equals(card1Dup));
	}
	
	public void equalsNotDuplicate() {
		assertFalse(card1.equals(card2));
	}
	
	/** Hashcode is the same hashcode as the symbol */
	public void hashCodeTest() {
		Arrays.asList(card1, card2, card3, card4)
			.parallelStream()
			.forEach(card -> assertEquals(card.hashCode(), card.getSymbol().hashCode()));
	}
}
