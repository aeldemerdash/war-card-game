package org.rpk.game.war;

import static org.mockito.Mockito.when;
import static org.rpk.game.war.Rules.Result.Player1Won;
import static org.rpk.game.war.Rules.Result.Player2Won;
import static org.rpk.game.war.Rules.Result.Tie;
import static org.rpk.game.war.card.Rank.Ace;
import static org.rpk.game.war.card.Rank.Deuce;
import static org.rpk.game.war.card.Suit.Diamonds;
import static org.rpk.game.war.card.Suit.Spades;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rpk.game.war.Rules.Result;
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
public class RulesTest {
	
	@Mock GameSession session;
	@Mock CardTable table;
	@Mock Player player1, player2;
	
	final Card 
		aceOfSpades = new Card(Ace, Spades)
		, deucdeOfDiamonds = new Card(Deuce, Diamonds);
	
	@BeforeMethod
	public void beforeMethod() {
		MockitoAnnotations.initMocks(this);
		when(session.getTable()).thenReturn(table);
		when(session.getPlayer1()).thenReturn(player1);
		when(session.getPlayer2()).thenReturn(player2);
		when(player1.getHand()).thenReturn(new LinkedList<>());
		when(player2.getHand()).thenReturn(new LinkedList<>());
	}

	@Test(expectedExceptions = RuntimeException.class)
	public void Rules() {
		new Rules();
	}
	
	public void getRoundResultPlayer1Won() {
		when(table.peekPlayer1CardStack()).thenReturn(aceOfSpades);
		when(table.peekPlayer2CardStack()).thenReturn(deucdeOfDiamonds);
		final Result result = Rules.getRoundResult(session);
		assertEquals(result, Player1Won);
	}

	public void getRoundResultPlayer2Won() {
		when(table.peekPlayer1CardStack()).thenReturn(deucdeOfDiamonds);
		when(table.peekPlayer2CardStack()).thenReturn(aceOfSpades);
		final Result result = Rules.getRoundResult(session);
		assertEquals(result, Player2Won);
	}
	
	public void getRoundResultTie() {
		when(table.peekPlayer1CardStack()).thenReturn(aceOfSpades);
		when(table.peekPlayer2CardStack()).thenReturn(aceOfSpades);
		final Result result = Rules.getRoundResult(session);
		assertEquals(result, Tie);
	}
	
	public void playerHasLostFalse() {
		player1.getHand().add(aceOfSpades);
		player2.getHand().add(deucdeOfDiamonds);
		assertFalse(Rules.playerHasLost(session));
	}

	public void playerHasLostPlayer1() {
		player2.getHand().add(deucdeOfDiamonds);
		assertTrue(Rules.playerHasLost(session));
	}

	public void playerHasLostPlayer2() {
		player1.getHand().add(aceOfSpades);
		assertTrue(Rules.playerHasLost(session));
	}
	
	public void getWinnerPlayer1() {
		player1.getHand().add(aceOfSpades);
		final Player winner = Rules.getWinner(player1, player2);
		assertSame(winner, player1);
	}

	public void getWinnerPlayer2() {
		player2.getHand().add(aceOfSpades);
		final Player winner = Rules.getWinner(player1, player2);
		assertSame(winner, player2);
	}
	
	@Test(expectedExceptions = RuntimeException.class)
	public void getWinnerNone() {
		Rules.getWinner(player1, player2);
	}
	
	public void valueOfRulesResult() {
			assertEquals(Stream.of(Result.values())
				.parallel()
				.map(result -> result.name())
				.map(name -> Result.valueOf(name))
				.filter(result -> null == result)
				.collect(Collectors.toList())
				.size(), 0);
	}
	
}
