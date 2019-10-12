package edu.smcm.ai.examples.solitaire.spider;

public class DealNewRowRecogniser extends Heuristic {
	
	@Override
	public int value(Game game, MoveStack move) {
		return false_value;
	}
	
	@Override
	public int value(Game game, DealNewRow move) {
		return true_value;
	}

	@Override
	public String abbreviation() {
		return "DNRR";
	}

	@Override
	public String fullName() {
		return "Deal New Row Recogniser";
	}
}
