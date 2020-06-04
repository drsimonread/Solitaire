package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Subcontext;
import edu.smcm.ai.genetic.algorithm.Heuristic;
import edu.smcm.ai.genetic.programming.Integer;
import edu.smcm.ai.genetic.programming.Value;

public class NumberOfCards extends Heuristic {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.algorithm.Subcontext subcontext) {
		Move move;
		
		move = ((Subcontext) subcontext).move();
		
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

}
