package edu.smcm.ai.genetic;

/**
 * An embodiment of the fitness of an individual.
 * 
 * This allows fitness to be more sophisticated than a simple double score.
 */
public abstract class Fitness implements Comparable<Fitness> {

	/**
	 * Compare two fitnesses.
	 * 
	 * Necessary to rank a population in terms of Fitness.
	 */
	public abstract int compareTo(Fitness that);
}
