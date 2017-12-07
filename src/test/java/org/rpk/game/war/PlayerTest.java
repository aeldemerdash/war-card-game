package org.rpk.game.war;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.rpk.game.war.Player.Player1Name;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpk.game.war.Player;
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
public class PlayerTest {

	@Mock CardTable table;
	@Mock Card card1, card2, card3, card4, card5, card6, card7, card8, card9, card10;
	@Mock Stack<Card> player1CardStack, player2CardStack;
	
	Player player;
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		player = new Player(Player1Name);
		when(table.getPlayer1Cards()).thenReturn(player1CardStack);
		when(table.getPlayer2Cards()).thenReturn(player2CardStack);
	}

	public void collectCardsSinglePlay() {
		when(table.takePlayer1Cards()).thenReturn(Arrays.asList(card1));
		when(table.takePlayer2Cards()).thenReturn(Arrays.asList(card2));
		player.collectCards(table);
		assertEquals(player.getHand().size(), 2);
		assertTrue(player.getHand().containsAll(Arrays.asList(card1, card2)));
	}

	public void collectCardsAfterWar() {
		when(table.takePlayer1Cards()).thenReturn(
			Arrays.asList(card1, card2, card3, card4, card5));
		when(table.takePlayer2Cards()).thenReturn(
			Arrays.asList(card6, card7, card8, card9, card10));
		player.collectCards(table);
		assertEquals(player.getHand().size(), 10);
		assertTrue(player.getHand().containsAll(
			Arrays.asList(card1, card2, card3, card4, card5
				, card6, card7, card8, card9, card10)));
	}

	public void getHand() {
		assertNotNull(player.getHand());
	}

	public void getName() {
		assertEquals(player.getName(), Player1Name);
	}

	public void getScore() {
		player.getHand().addAll(Arrays.asList(card1, card2, card3));
		assertEquals(player.getScore(), 3);
	}

	public void playCard() {
		player.getHand().add(card1);
		player.playCard(table.getPlayer1Cards());
		verify(player1CardStack, times(1)).push(any(Card.class));
		verify(player1CardStack, times(1)).push(card1);
	}

	public void playThreeCards() {
		List<Card> cards = Arrays.asList(card1, card2, card3);
		player.getHand().addAll(cards);
		player.playThreeCards(table.getPlayer1Cards());
		verify(player1CardStack, times(3)).push(any(Card.class));
		cards.stream().forEachOrdered(card -> 
			verify(player1CardStack, times(1)).push(card));
		
	}
	
	public void playThreeCardsWithLessCards() {
		List<Card> cards = Arrays.asList(card1, card2);
		player.getHand().addAll(cards);
		player.playThreeCards(table.getPlayer1Cards());
		verify(player1CardStack, times(2)).push(any(Card.class));
		cards.stream().forEachOrdered(card -> 
			verify(player1CardStack, times(1)).push(card));
	}
	
}
