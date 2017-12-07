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

	public void getPlayer1CardStack() {
		assertNotNull(table.getPlayer1CardStack());
	}

	public void getPlayer2CardStack() {
		assertNotNull(table.getPlayer2CardStack());
	}

	@Test(expectedExceptions = EmptyStackException.class)
	public void peekPlayer1CardStackEmpty() {
		table.peekPlayer1CardStack();
	}
	
	public void takePlayer1CardStack() {
		table.getPlayer1CardStack().push(card1);
		List<Card> cards = table.takePlayer1CardStack();
		assertEquals(table.getPlayer1CardStack().size(), 0);
		assertEquals(cards.size(), 1);
		assertTrue(cards.contains(card1));
	}
	
	public void takePlayer2CardStack() {
		table.getPlayer2CardStack().push(card1);
		List<Card> cards = table.takePlayer2CardStack();
		assertEquals(table.getPlayer2CardStack().size(), 0);
		assertEquals(cards.size(), 1);
		assertTrue(cards.contains(card1));
	}
	
	public void peekPlayer1CardStack() {
		table.getPlayer1CardStack().push(card1);
		assertSame(table.peekPlayer1CardStack(), card1);
		table.getPlayer1CardStack().push(card2);
		assertSame(table.peekPlayer1CardStack(), card2);
		table.getPlayer1CardStack().pop();
		assertSame(table.peekPlayer1CardStack(), card1);
	}

	@Test(expectedExceptions = EmptyStackException.class)
	public void peekPlayer2CardStackEmpty() {
		table.peekPlayer2CardStack();
	}

	public void peekPlayer2CardStack() {
		table.getPlayer2CardStack().push(card1);
		assertSame(table.peekPlayer2CardStack(), card1);
		table.getPlayer2CardStack().push(card2);
		assertSame(table.peekPlayer2CardStack(), card2);
		table.getPlayer2CardStack().pop();
		assertSame(table.peekPlayer2CardStack(), card1);
	}
}
