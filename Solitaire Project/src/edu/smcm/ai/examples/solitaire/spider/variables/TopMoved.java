package edu.smcm.ai.examples.solitaire.spider.variables;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Integer;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;

public class TopMoved extends Variable {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		
		game = ((Position) position).game();
		move = ((Position) position).move();

		return new Integer(game.cardAt(move.from(), move.cards() - 1).value());
	}

	@Override
	public String abbreviation() {
		return "TM";
	}

	@Override
	public String fullName() {
		return "Top Moved";
	}

	@Override
	public DataType dataType() {
		return DataType.Integer;
	}

}
