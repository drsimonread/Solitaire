package edu.smcm.ai.genetic;

import java.util.Random;

import edu.smcm.util.Pair;

/**
 * An abstract class to represent the central idea of a genotype.
 * 
 * The genotype will take on different forms for different genetic methods, but
 * this class abstracts them so that they can be used in the general algorithm
 * in Population.
 */
public abstract class Genotype {

	/**
	 * The system-wide random number generator.
	 */
	private static Random random;

	/**
	 * Set the system-wide random number generator.
	 * 
	 * Failure to do this when initialising the program will result in a
	 * NullPointerException.
	 * 
	 * @param random The system-wide random number generator.
	 */
	public static void random(Random random) {
		Genotype.random = random;
	}

	/**
	 * Allow subclasses to access random number generator.
	 * 
	 * @return The random number generator.
	 */
	protected Random random() {
		return random;
	}

	/**
	 * Provide a mutated *copy* of this Genotype.
	 * 
	 * Will preserve the Genotype on which this is called.
	 * 
	 * @return The mutated copy.
	 */
	public abstract Genotype mutate();

	/**
	 * Provide a crossed over *copy* of this Genotype.
	 * 
	 * Will preserve the Genotype of which this is called.
	 * 
	 * @param that The genotype to crossover with.
	 * @return The crossed over copy.
	 */
	public abstract Pair<Genotype, Genotype> crossover(Genotype that);
	
	public abstract double evaluate(Position position);
}