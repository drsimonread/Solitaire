package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.Boolean;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;

public class Discovery extends Variable {

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

	@Override
	public DataType dataType() {
		return DataType.Boolean;
	}

}
