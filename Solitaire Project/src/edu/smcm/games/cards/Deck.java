package edu.smcm.games.cards;

import java.util.ArrayList;
import java.util.List;

public class Deck {

	private List<Card> cards;
		
	public Deck() {
		this.cards = new ArrayList<Card>(52);

		// TODO Should we add in Jokers by default
		
		for (Suit suit : Suit.values()) {
			for (int face_value = 0; face_value < Card.cards_in_suit; face_value++) {
				cards.add(new Card(suit, face_value + 1));
			}
		}
	}
	
	public Card deal() {
		return cards.remove(0);
	}
	
	public boolean isEmpty() {
		return 0 == cards.size();
	}
	
	public void shuffle() {
		Cards.shuffle(cards);
	}
}
