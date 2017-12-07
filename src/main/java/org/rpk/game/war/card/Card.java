package org.rpk.game.war.card;

/**
 * A playing card.
 * 
 * @author R. Paul Kennedy 
 */
public class Card implements Comparable<Card> {

	protected final Rank rank;
	protected final Suit suit;
	
	/** String representation of a card (Rank symbol + Suit symbol) */
	protected final String symbol;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.symbol = String.format("%s%s", rank.symbol(), suit.symbol());
	}

	public Suit getSuit() {
		return suit;
	}

	public Rank getRank() {
		return rank;
	}

	public String getSymbol() {
		return this.symbol;
	}
	
	/**
	 * @return Ordinal rank value regardless of suit.
	 */
	public int getValue() {
		return this.getRank().ordinal();
	}

	public String toString() {
		return this.getSymbol();
	}

	@Override
	public int compareTo(Card that) {
		final Integer 
			thisValue = this.getValue()
			, thatValue = that.getValue();
		return thisValue.compareTo(thatValue);
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		else if (!this.getClass().isInstance(that)) {
			return false;
		}
		else {
			return this.getSymbol().equals(((Card)that).getSymbol());
		}
	}

	@Override
	public int hashCode() {
		return getSymbol().hashCode();
	}

}
