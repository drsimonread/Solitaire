package edu.smcm.games.cards;

/**
 * An Exceotion for when some of the Cards requested are face down.
 */
@SuppressWarnings("serial")
public class CardsFaceDownException extends Exception {

	/**
	 * The number of Cards requested.
	 */
	private int requested;
	
	/**
	 * Constructor for the Exception.
	 * 
	 * @param requested The number of cards requested.
	 */
	public CardsFaceDownException(int requested) {
		this.requested = requested;
	}
	
	@Override
	public String toString() {
		return "The " + requested + "th card is face down.";
	}
}
