package edu.smcm.ai.genetic;

import java.util.Random;

public abstract class Individual implements Comparable<Individual> {

	/**
	 * The system-wide random number generator.
	 */
	private static Random random;
	
	/**
	 * The system-wide object factory.
	 */
	private static Factory factory;

	/**
	 * The fitness of this individual as evaluated in the current Context.
	 */
	private Fitness fitness;
	
	/**
	 * The genotype of this individual.
	 */
	private Genotype genotype;

	/**
	 * Set the system-wide random number generator.
	 * 
	 * Failing to set this at the start of the program will result in a
	 * NullPointerException.
	 * 
	 * @param random The system-wide random number generator.
	 */
	public static void random(Random random) {
		Individual.random = random;
	}

	/**
	 * Allow access to random number generator from subclasses.
	 * 
	 * @return The random number generator.
	 */
	protected Random random() {
		return random;
	}
	
	/**
	 * Set the system-wide object factory.
	 * 
	 * Failing to set this at the start of the program will result in a NullPointerException.
	 * 
	 * @param factory The system-wide object factory.
	 */
	public static void factory(Factory factory) {
		Individual.factory = factory;
	}
	
	/**
	 * Evaluate the fitness of this individual in the provided Context.
	 * 
	 * NOTE: The implementation of this method might mutate the Context. If this
	 * might be a problem then the Context should be copied first.
	 * 
	 * Sets the fitness of this individual for later sorting.
	 * 
	 * @param context Context in which to evaluate.
	 */
	public abstract void evaluate(Context context);


	public Individual crossover(Individual other) {
		return factory.makeIndividual(genotype.crossover(other.genotype));
	}

	public Individual mutate() {
		return factory.makeIndividual(genotype.mutate());
	}
		
	@Override
	public int compareTo(Individual that) {
		return fitness.compareTo(that.fitness);
	}
}
