package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.algorithm.Heuristic;
import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.programming.Boolean;

public class Discovery extends Heuristic {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		
		game = ((Position) position).game();
		move = ((Position) position).move();
		
		return new Boolean(move.cards() < game.cardsInStack(move.from()) &&
				!game.cardAt(move.from(), move.cards()).faceUp());
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
