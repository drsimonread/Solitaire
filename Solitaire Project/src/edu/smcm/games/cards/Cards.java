package edu.smcm.games.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Utilities for Cards
 */
public class Cards {

	/**
	 * A random number generator for Cards.
	 */
	private static Random oracle;

	// Set a default random number generator.
	static {
		oracle = new Random();
	}
	
	/**
	 * Set a system-wide random number generator.
	 * 
	 * @param random The system-wide random number generator.
	 */
	public static void random(Random random) {
		Cards.oracle = random;
	}
	
	/**
	 * Private constructor to prevent actual instantiaion.
	 */
	private Cards() {
	}
	
	/**
	 * Shuffle a List of cards.
	 * 
	 * NOTE: This method mutates the List of Cards that is supplied.
	 * 
	 * TODO Ensure that the order of the cards is truly random.
	 * 
	 * @param cards The Cards to be shuffled.
	 */
	public static void shuffle(List<Card> cards) {
		List<Card> shuffled; // The List of Cards when shuffled
		int position; // A temporary value for the position of the next card
		
		// A List to copy the Cards into
		shuffled = new ArrayList<Card>(cards.size());
		
		// Copy the Cards one by one into the temporary list
		while (0 < cards.size()) {
			position = oracle.nextInt(cards.size());
			shuffled.add(cards.remove(position));
		}
		
		// Copy the cards into the original List
		cards.addAll(shuffled);
	}
}
