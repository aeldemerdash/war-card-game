package org.rpk.game.war;


/**
 * <p>Runs the game.</p>
 * <p>To run the game:</p>
 * <ol>
 * 	<li>Ensure <code>Java 8</code> is installed and on your classpath.</li>
 * 	<li>In a console, go to the directory of <code>war-card-game.jar</code>, 
 * 	which should be <code>the/path/to/war-card-game/target</code>.</li>
 *  <li>Enter the command '<code>java -jar war-card-game.jar</code>'.</li>
 *  <li>Alternatively, enter '<code>java -jar war-card-game.jar -y</code>'
 *  	to run the game without user input for each round.</li>
 * </ol>
 *
 * @author R. Paul Kennedy 
 */
public class WarGameRunner {
	
	public static void main(String[] args) {
		new WarGame().run(args);
	}
	
	/** No instances allowed 
	 * @throws RuntimeException */
	protected WarGameRunner() {
		throw new RuntimeException("No instances allowed.");
	}
}
