package org.rpk.game.war;

import static org.rpk.game.war.Player.Player1Name;
import static org.rpk.game.war.Player.Player2Name;

import org.rpk.game.war.card.CardTable;
import org.rpk.game.war.card.Dealer;

/**
 * Holds game session information.
 *
 * @author R. Paul Kennedy
 */
public class GameSession {
	protected final Player player1;
	protected final Player player2;
	protected final CardTable table;
	protected final boolean skipPlayerInput;

	public GameSession(Player player1, Player player2, CardTable table, boolean skipPlayerInput) {
		this.player1 = player1;
		this.player2 = player2;
		this.table = table;
		this.skipPlayerInput = skipPlayerInput;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public CardTable getTable() {
		return table;
	}

	public boolean isSkipPlayerInput() {
		return skipPlayerInput;
	}

	public static GameSession newInstance(boolean skipPlayerInput) {
		Player player1 = new Player(Player1Name);
		Player player2 = new Player(Player2Name);
		new Dealer().deal(player1, player2);
		CardTable table = new CardTable();
		return new GameSession(player1, player2, table, skipPlayerInput);
	}

}
