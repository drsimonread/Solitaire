package edu.smcm.games.cards;

public enum Suit {
	Clubs('C'),
	Diamonds('D'),
	Hearts('H'),
	Spades('S');

	private char representation;
	
	private Suit(char representation) {
		this.representation = representation;
	}
	
	public String toString() {
		return "" + representation;
	}

}
