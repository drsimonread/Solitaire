package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Heuristic;
import edu.smcm.ai.examples.solitaire.spider.MoveStack;

public class TopMoved extends Heuristic {

	@Override
	public int value(Game game, MoveStack move) {
		return game.cardAt(move.from(), move.cards() - 1).value();
	}

	@Override
	public String abbreviation() {
		return "TM";
	}

	@Override
	public String fullName() {
		return "Top Moved";
	}

}
