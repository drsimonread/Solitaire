package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.MoveStack;
import edu.smcm.ai.genetic.algorithm.Heuristic;

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
