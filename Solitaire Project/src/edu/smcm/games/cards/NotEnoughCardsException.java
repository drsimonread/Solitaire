package edu.smcm.games.cards;

/**
 * Exception to be thrown is there are not enough Cards for the attempted method.
 *
 */
@SuppressWarnings("serial")
public class NotEnoughCardsException extends Exception {

	/**
	 * The number of cards that was requested.
	 */
	private int desired;
	
	/**
	 * The number of cards that was available. 
	 */
	private int available;
	
	/**
	 * Constructor for the Exception
	 * 
	 * @param desired The number of Cards requested.
	 * @param available The number of Cards available.
	 */
	public NotEnoughCardsException(int desired, int available) {
		this.desired = desired;
		this.available = available;
	}
	
	/**
	 * Get the number of Cards requested.
	 * 
	 * @return The number of Cards requested.
	 */
	public int desired() {
		return this.desired;
	}
	
	/**
	 * Get the number of Cards available.
	 * 
	 * @return The number of Cards available.
	 */
	public int available() {
		return this.available;
	}
	
	/**
	 * Convert the Exception to a String for printing.
	 * 
	 * @return Formatted String.
	 */
	@Override
	public String toString() {
		return desired + " cards were requested, but only " + available + "were available.";
	}
}
