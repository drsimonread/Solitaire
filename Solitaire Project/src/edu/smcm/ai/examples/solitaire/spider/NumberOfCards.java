package edu.smcm.ai.examples.solitaire.spider;

public class NumberOfCards extends Heuristic {

	@Override
	public int value(Game game, MoveStack move) {
		return move.cards();
	}

	@Override
	public String abbreviation() {
		return "NoC";
	}

	@Override
	public String fullName() {
		return "Number of Cards";
	}

}
