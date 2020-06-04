package edu.smcm.ai.genetic.algorithm;

import java.util.Random;

import edu.smcm.ai.genetic.Position;
import edu.smcm.ai.genetic.programming.Value;

/**
 * A Heuristic that provides a value in a Subcontext.
 */
public abstract class Heuristic {
	
	private static Random random;
	
	static {
		random = new Random();
	}
	
	public static void random(Random random) {
		Heuristic.random = random;
	}
	
	protected Random random() {
		return random;
	}
	
	public abstract Value evaluate(Position subcontext);
	
	public abstract String abbreviation();
	
	public abstract String fullName();
}
