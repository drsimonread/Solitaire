package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.games.cards.Suit;
import edu.smcm.util.Util;

/**
 * @author sread
 * Note top and bottom are the same if the range contains just 1 card.
 */
public class Range {

	private int bottom;
	private int top;
	private Suit suit;
	
	public Range(int bottom, int top, Suit suit) {
		this.bottom = bottom;
		this.top = top;
		this.suit = suit;
	}
	
	public int bottom() {
		return bottom;
	}
	
	public int top() {
		return top;
	}
	
	public Suit suit() {
		return suit;
	}
	
	/**
	 * Determine if a card value is in the Range.
	 * 
	 * @param value to check if in Range.
	 * @return the value is in the Range.
	 */
	public boolean contains(int value) {
		return Util.isBetween(bottom, value, top);
	}
	
	/**
	 * Get the number of cards in the Range.
	 * 
	 * @return number of cards.
	 */
	public int size() {
		return top - bottom + 1;	
	}
	
	public String toString() {
		return "From " + bottom + " to " + top + " in " + suit;
	}
}
