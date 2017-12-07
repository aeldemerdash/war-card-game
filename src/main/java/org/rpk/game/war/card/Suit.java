package org.rpk.game.war.card;

/**
 * Card suit (Diamonds, Clubs, Hearts, & Spades).
 *
 * @author R. Paul Kennedy 
 */
public enum Suit {

	Diamonds('D')
	, Clubs('C')
	, Hearts('H')
	, Spades('S');

	/** Suit symbol (D, C, H, S)*/
	final char symbol;

	Suit(char symbol) {
		this.symbol = symbol;
	}

	public char symbol() {
		return this.symbol;
	}
	
}
