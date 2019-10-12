package edu.smcm.ai.examples.solitaire.spider;

public class Discovery extends Heuristic {

	@Override
	public int value(Game game, MoveStack move) {
		int result;
		
		if (move.cards() < game.cardsInStack(move.from()) &&
				!game.cardAt(move.from(), move.cards()).faceUp()) {
			result = true_value;
		} else {
			result = false_value;
		}
		
		return result;
	}

	@Override
	public String abbreviation() {
		return "DISC";
	}

	@Override
	public String fullName() {
		return "Discovery";
	}

}
