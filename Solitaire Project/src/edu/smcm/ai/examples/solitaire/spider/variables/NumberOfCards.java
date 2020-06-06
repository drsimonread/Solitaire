package edu.smcm.ai.examples.solitaire.spider.variables;

import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Integer;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;

public class NumberOfCards extends Variable {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Move move;
		
		move = ((Position) position).move();
		
		return new Integer(move.cards());
	}

	@Override
	public String abbreviation() {
		return "NoC";
	}

	@Override
	public String fullName() {
		return "Number of Cards";
	}

	@Override
	public DataType dataType() {
		return DataType.Boolean;
	}

}
