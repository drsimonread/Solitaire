package edu.smcm.games.cards;

/**
 * Enumeration for the Suits.
 * 
 * Suits are comparable in Bridge order.
 * 
 */
public enum Suit {
	Clubs('C'),
	Diamonds('D'),
	Hearts('H'),
	Spades('S');

	/**
	 * The character representation of the Suit. 
	 */
	private char representation;
	
	/**
	 * The private constructor for members of the enumeration.
	 * 
	 * The character used to represent a suit is the first letter of its English name.
	 * 
	 * @param representation The character representation of the Suit.
	 */
	private Suit(char representation) {
		this.representation = representation;
	}
	
	/**
	 * Representation of the Suit as a single character String.
	 */
	@Override
	public String toString() {
		return "" + representation;
	}

}
