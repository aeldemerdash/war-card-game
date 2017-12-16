package org.rpk.game.war.card;

/**
 * Card rank from Deuce (2) to Ace (A).
 *
 * @author R. Paul Kennedy 
 */
public enum Rank {

	Deuce('2')
	, Three('3')
	, Four('4')
	, Five('5')
	, Six('6')
	, Seven('7')
	, Eight('8')
	, Nine('9')
	, Ten('T')
	, Jack('J')
	, Queen('Q')
	, King('K')
	, Ace('A');

	/** Rank symbol (2,3,4,...,A) */
	final char symbol;

	Rank(char symbol) {
		this.symbol = symbol;
	}

	public char symbol() {
		return this.symbol;
	}
	
}
