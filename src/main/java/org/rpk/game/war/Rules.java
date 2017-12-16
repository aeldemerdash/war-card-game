package org.rpk.game.war;


import static org.rpk.game.war.Rules.Result.Player1Won;
import static org.rpk.game.war.Rules.Result.Player2Won;
import static org.rpk.game.war.Rules.Result.Tie;

import java.util.stream.Stream;

import org.rpk.game.war.card.Card;

/**
 * Rules of War.
 *
 * @author R. Paul Kennedy 
 */
public class Rules {
	
	/** Result of a round */
	public enum Result {
		Player1Won
		, Player2Won
		, Tie
	}
	
	/** No instances allowed 
	 * @throws RuntimeException */
	protected Rules() {
		throw new RuntimeException("No instances allowed.");
	}
	
	/**
	 * @param session
	 * @return Result of the latest round.
	 */
	public static Result getRoundResult(GameSession session) {
		// Apply the rules
		if (thisCardWon(session.getTable().peekPlayer1CardStack(), session.getTable().peekPlayer2CardStack())) {
			return Player1Won;
		}
		else if (thatCardWon(session.getTable().peekPlayer1CardStack(), session.getTable().peekPlayer2CardStack())) {
			return Player2Won;
		}
		else {
			return Tie;
		}
	}
	
	
	/**
	 * @param session
	 * @return Whether any Player in {@code session} has lost.
	 */
	public static boolean playerHasLost(GameSession session) {
		return playerHasLost(session.getPlayer1(), session.getPlayer2());
	}

	/**
	 * @param players
	 * @return Whether any Player in {@code players} has lost.
	 */
	protected static boolean playerHasLost(Player... players) {
		return Stream.of(players)
			.parallel()
			.anyMatch(player -> player.getHand().size() == 0);
	}
	
	/**
	 * @param players
	 * @return Any player with any cards left.
	 */
	public static Player getWinner(Player... players) {
		return Stream.of(players)
			.parallel()
			.filter(player -> player.getHand().size() > 0)
			.findAny()
			.orElseThrow(RuntimeException::new);
	}
	
	/**
	 * @param thisCard
	 * @param thatCard
	 * @return Whether {@code thisCard} beat {@code thatCard}.
	 */
	protected static boolean thisCardWon(Card thisCard, Card thatCard) {
		return thisCard.compareTo(thatCard) > 0;
	}
	
	/**
	 * @param thisCard
	 * @param thatCard
	 * @return Whether {@code thatCard} beat {@code thisCard}.
	 */
	protected static boolean thatCardWon(Card thisCard, Card thatCard) {
		return thisCard.compareTo(thatCard) < 0;
	}
	
}

