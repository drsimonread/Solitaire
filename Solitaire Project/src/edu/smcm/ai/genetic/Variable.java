package edu.smcm.ai.genetic;

import java.util.Random;

/**
 * A Heuristic that provides a value in a Subcontext.
 */
public abstract class Variable {
	
	private static Random random;
	
	static {
		random = new Random();
	}
	
	public static void random(Random random) {
		Variable.random = random;
	}
	
	protected Random random() {
		return random;
	}
	
	public abstract Value evaluate(Position subcontext);
	
	public abstract DataType dataType();
	
	public abstract String abbreviation();
	
	public abstract String fullName();
}
