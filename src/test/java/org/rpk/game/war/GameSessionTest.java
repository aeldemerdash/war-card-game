package org.rpk.game.war;

import org.testng.annotations.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.rpk.game.war.Player.Player1Name;
import static org.rpk.game.war.Player.Player2Name;
import static org.testng.Assert.assertEquals;

import org.rpk.game.war.GameSession;
import org.testng.annotations.BeforeMethod;

/**
 * Unit test.
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class GameSessionTest {

	GameSession session;

	@BeforeMethod
	public void beforeMethod() {
		session = GameSession.newInstance(true);
	}

	/** Player 1 should be named 'Player' */
	public void getPlayer1() {
		assertEquals(session.getPlayer1().getName(), Player1Name);
	}

	/** Player 2 should be named 'Computer' */
	public void getPlayer2() {
		assertEquals(session.getPlayer2().getName(), Player2Name);
	}

	public void getTable() {
		assertNotNull(session.getTable());
	}
	
	public void isSkipPlayerInput() {
		assertTrue(session.isSkipPlayerInput());
	}
}
