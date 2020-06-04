package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.programming.Boolean;
import edu.smcm.ai.genetic.programming.Value;

public class CreatedEmptyStack extends edu.smcm.ai.genetic.algorithm.Heuristic {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		
		
		game = ((Position) position).game();
		move = ((Position) position).move();
		
		return new Boolean(game.cardsInStack(move.from()) == move.cards());
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
