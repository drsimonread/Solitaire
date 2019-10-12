package edu.smcm.games.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cards {

	private static Random oracle;

	static {
		oracle = new Random();
	}
	
	private Cards() {
	
	}
	
	// TODO Should this return a copy of the List?
	public static void shuffle(List<Card> cards) {
		List<Card> shuffled;
		int position;
		
		shuffled = new ArrayList<Card>(cards.size());
		
		while (0 < cards.size()) {
			position = oracle.nextInt(cards.size());
			shuffled.add(cards.remove(position));
		}
		
		cards.addAll(shuffled);

	}
}
