package org.rpk.game.war;

import org.testng.annotations.Test;

/**
 * System test (Needs visual verification).
 * 
 * @author R. Paul Kennedy 
 */
@Test
public class WarGameTest {

	/** 
	 * Run the game with the -y option to skip user input.
	 * MANUALY examine the console output for discrepancies.
	 */
	@Test(timeOut = 60*1000)
	public void run() {
		WarGameRunner.main(new String[] { "-y" });
	}
	
	/** No instances allowed. */
	@Test(expectedExceptions = RuntimeException.class)
	public void WarGameRunner() {
		new WarGameRunner();
	}
	
	@Test(expectedExceptions = NullPointerException.class)
	public void evaluateRoundNull() {
		WarGame.evaluateRound(null, null);
	}
}
