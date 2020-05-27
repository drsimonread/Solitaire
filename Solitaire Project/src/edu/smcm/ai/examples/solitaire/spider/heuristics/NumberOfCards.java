package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.MoveStack;
import edu.smcm.ai.genetic.algorithm.Heuristic;

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
