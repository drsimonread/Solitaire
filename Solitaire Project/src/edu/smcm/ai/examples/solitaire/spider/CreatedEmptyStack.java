package edu.smcm.ai.examples.solitaire.spider;

public class CreatedEmptyStack extends Heuristic {

	@Override
	public int value(Game game, MoveStack move) {
		return game.cardsInStack(move.from()) == move.cards() ? true_value : false_value;
	}

	@Override
	public String abbreviation() {
		return "CES";
	}

	@Override
	public String fullName() {
		return "Created Empty Stack";
	}
}
