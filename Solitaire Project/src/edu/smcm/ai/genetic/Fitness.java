package edu.smcm.ai.genetic;

/**
 * An embodiment of the fitness of an individual.
 * 
 * This allows fitness to be more sophisticated than a simple double score.
 */
public abstract class Fitness implements Comparable<Fitness> {

	// TODO Should Fitness be a list of Metrics?
	
	/**
	 * Update this Fitness using another.
	 * 
	 * Used to create a cumulative Fitness over several evaluations of a Genotype.
	 * 
	 * @param fitness The Fitness to update with.
	 */
	public abstract void update(Fitness fitenss);

	/**
	 * Compare two fitnesses.
	 * 
	 * Necessary to rank a population in terms of Fitness.
	 */
	public abstract int compareTo(Fitness that);
}
