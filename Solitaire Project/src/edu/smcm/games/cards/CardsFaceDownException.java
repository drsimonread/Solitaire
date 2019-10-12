package edu.smcm.games.cards;

@SuppressWarnings("serial")
public class CardsFaceDownException extends Exception {

	private int requested;
	
	public CardsFaceDownException(int requested) {
		this.requested = requested;
	}
	
	public String toString() {
		return "The " + requested + "th card is face down.";
	}
}
