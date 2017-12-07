package org.rpk.game.war;


import java.io.IOException;
import java.util.Arrays;

import org.rpk.game.war.Rules;
import org.rpk.game.war.Rules.Result;

/**
 * A simple card game of chance called 'War' written in Java 8.
 *
 * @author R. Paul Kennedy 
 */
public class WarGame {

	/**
	 * Main loop that runs the game.
	 * 
	 * @param args ('-y' option bypasses user prompts).
	 */
	public void run(String[] args) {

		// Initialize the game session.
		final GameSession session = GameSession.newInstance(isSkipPlayerInput(args));

		printIntro();

		// Play the game as long as no player has lost.
		while (!Rules.playerHasLost(session)) {

			printScores(session);

			promptForGamePlay(session);

			playCards(session);

			printCardValues(session);

			evaluateRound(session);
		}

		printOutro(session);
	}

	public static void print(String fmt, Object... args) {
		System.out.printf(fmt + "\n", args);
	}

	protected static void printIntro() {
		print("************************");
		print("*** Let's play War! ****");
		print("************************\n");
	}

	protected static void printScores(GameSession session) {
		print("Scores:");
		print("%s [%s] | %s [%s]", session.getPlayer1().getName(), session.getPlayer1().getScore(),
				session.getPlayer2().getName(), session.getPlayer2().getScore());
	}

	protected static void promptForGamePlay(GameSession session) {
		print("Press 'Enter' to play.");
		if (!session.isSkipPlayerInput()) {
			try {
				System.in.read();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	protected static void playCards(GameSession session) {
		session.getPlayer1().playCard(session.getTable().getPlayer1Cards());
		session.getPlayer2().playCard(session.getTable().getPlayer2Cards());
	}

	protected static void printCardValues(GameSession session) {
		print("Cards Played:");
		print("%s {%s} | %s {%s}", session.getPlayer1().getName(), session.getTable().peekPlayer1Card(),
				session.getPlayer2().getName(), session.getTable().peekPlayer2Card());
	}
	
	protected static void evaluateRound(GameSession session) {
		evaluateRound(session, Rules.getRoundResult(session));
	}
	
	protected static void evaluateRound(GameSession session, Result result) {
		switch (result) {
			case Player1Won: {
				// Give all the cards on the table to player 1.
				printWinner(session.getPlayer1());
				collectCards(session.getPlayer1(), session);
				break;
			}
			case Player2Won: {
				// Give all the cards on the table to player 2.
				printWinner(session.getPlayer2());
				collectCards(session.getPlayer2(), session);
				break;
			}
			case Tie:
			default: {
				// Put 3 cards on the table from both players.
				printWar();
				play3Cards(session);
			}
		}		
	}
	
	protected static void printWinner(Player player) {
		print("%s wins!", player.getName());
	}
	
	protected static void collectCards(Player player, GameSession session) {
		player.collectCards(session.getTable());
	}
	
	protected static void printWar() {
		final String war = 
		  "#     #    #    ######  \n"
		+ "#  #  #   # #   #     # \n"
		+ "#  #  #  #   #  #     # \n"
		+ "#  #  # #     # ######  \n"
		+ "#  #  # ####### #   #   \n"
		+ "#  #  # #     # #    #  \n"
		+ " ## ##  #     # #     # ";
		print(war);
	}
	
	protected static void play3Cards(GameSession session) {
		session.getPlayer1().playThreeCards(session.getTable().getPlayer1Cards());
		session.getPlayer2().playThreeCards(session.getTable().getPlayer2Cards());		
	}
	
	protected static void printOutro(GameSession session) {
		Player winner = Rules.getWinner(session.getPlayer1(), session.getPlayer2());
		print("************************");
		print("****** Game Over! ******");
		print("************************");
		print("Winner: %s", winner.getName());
	}
	
	/* Whether the game waits for player input, or auto-plays the entire game. */
	protected static boolean isSkipPlayerInput(String[] args) {
		return Arrays.asList(args).parallelStream().anyMatch(s -> "-y".equals(s));
	}

}
