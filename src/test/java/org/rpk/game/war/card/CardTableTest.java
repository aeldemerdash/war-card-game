package org.rpk.game.war.card;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.EmptyStackException;
import java.util.List;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpk.game.war.card.Card;
import org.rpk.game.war.card.CardTable;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class CardTableTest {

	@Mock Card card1, card2;
	
	CardTable table;
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		table = new CardTable();
	}

	public void getPlayer1Cards() {
		assertNotNull(table.getPlayer1Cards());
	}

	public void getPlayer2Cards() {
		assertNotNull(table.getPlayer2Cards());
	}

	@Test(expectedExceptions = EmptyStackException.class)
	public void peekPlayer1CardEmpty() {
		table.peekPlayer1Card();
	}
	
	public void takePlayer1Cards() {
		table.getPlayer1Cards().push(card1);
		List<Card> cards = table.takePlayer1Cards();
		assertEquals(table.getPlayer1Cards().size(), 0);
		assertEquals(cards.size(), 1);
		assertTrue(cards.contains(card1));
	}
	
	public void takePlayer2Cards() {
		table.getPlayer2Cards().push(card1);
		List<Card> cards = table.takePlayer2Cards();
		assertEquals(table.getPlayer2Cards().size(), 0);
		assertEquals(cards.size(), 1);
		assertTrue(cards.contains(card1));
	}
	
	public void peekPlayer1Card() {
		table.getPlayer1Cards().push(card1);
		assertSame(table.peekPlayer1Card(), card1);
		table.getPlayer1Cards().push(card2);
		assertSame(table.peekPlayer1Card(), card2);
		table.getPlayer1Cards().pop();
		assertSame(table.peekPlayer1Card(), card1);
	}

	@Test(expectedExceptions = EmptyStackException.class)
	public void peekPlayer2CardEmpty() {
		table.peekPlayer2Card();
	}

	public void peekPlayer2Card() {
		table.getPlayer2Cards().push(card1);
		assertSame(table.peekPlayer2Card(), card1);
		table.getPlayer2Cards().push(card2);
		assertSame(table.peekPlayer2Card(), card2);
		table.getPlayer2Cards().pop();
		assertSame(table.peekPlayer2Card(), card1);
	}
}
