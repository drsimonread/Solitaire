package edu.smcm.games.cards;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards;
		
	/**
	 * Create a Deck without Jokers.
	 * 
	 * Note: Card are not in Bridge order.  Specifically, Aces are low.
	 */
	public Deck() {
		// Create an empty list of cards
		this.cards = new ArrayList<Card>(52);
		
		// Create all the cards in order
		for (Suit suit : Suit.values()) {
			for (int face_value = 0; face_value < Card.cards_in_suit; face_value++) {
				cards.add(new Card(suit, face_value + 1));
			}
		}
	}

	// TODO Add constructor for a Deck in Bridge order.
	
	// TODO Add a constructor for a Deck with Jacks.
	
	/**
	 * Deal the top card from the deck.
	 * 
	 * @return The top Card of the Deck.
	 */
	public Card deal() {
		return cards.remove(0);
	}
	
	/**
	 * Check to see if the Deck is empty.
	 * 
	 * @return True if the Deck is empty.
	 */
	public boolean isEmpty() {
		return 0 == cards.size();
	}
	
	/**
	 * Shuffle the cards (remaining) in the Deck.
	 */
	public void shuffle() {
		Cards.shuffle(cards);
	}
}
