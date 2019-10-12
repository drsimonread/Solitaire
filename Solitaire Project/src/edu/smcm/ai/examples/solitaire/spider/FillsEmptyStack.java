package edu.smcm.ai.examples.solitaire.spider;

public class FillsEmptyStack extends Heuristic {

	@Override
	public int value(Game game, MoveStack move) {
		return game.cardsInStack(move.to()) == 0 ? true_value : false_value;
	}

	@Override
	public String abbreviation() {
		return "FES";
	}

	@Override
	public String fullName() {
		return "Fills Empty Stack";
	}

}
