package edu.smcm.games.cards;

@SuppressWarnings("serial")
public class NotEnoughCardsException extends Exception {

	private int desired;
	private int available;
	
	public NotEnoughCardsException(int desired, int available) {
		this.desired = desired;
		this.available = available;
	}
	
	public String toString() {
		return desired + " cards were requested, but only " + available + "were available.";
	}
}
