package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.ai.examples.solitaire.spider.heuristics.DealNewRow;

public abstract class Heuristic {
	
	public static int true_value;
	public static int false_value;
	
	static {
		true_value = 10;
		false_value = 0;
	}

	// TODO Will this satisfy the polymorphism for Move or how do we do it?
	public abstract int value(Game game, MoveStack move);
	
	public int value(Game game, DealNewRow move) {
		return false_value;
	}
	
	public abstract String abbreviation();
	
	public abstract String fullName();
}
