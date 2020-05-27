package edu.smcm.ai.genetic.algorithm;

/**
 * A Heuristic that provides a value in a Context.
 */
public abstract class Heuristic {
	
	public static int true_value;
	public static int false_value;
	
	static {
		true_value = 10;
		false_value = 0;
	}
	
	public abstract String abbreviation();
	
	public abstract String fullName();
}
