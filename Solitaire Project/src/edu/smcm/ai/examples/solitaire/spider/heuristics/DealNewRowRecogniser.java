package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Heuristic;
import edu.smcm.ai.examples.solitaire.spider.MoveStack;

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
