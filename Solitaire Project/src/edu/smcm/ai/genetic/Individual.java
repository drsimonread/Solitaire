package edu.smcm.ai.genetic;

import java.util.Random;

/**
 * Embodiment of an Individual within the Population.
 * 
 * The individual consists mostly of a wrapper for a Genotype, but adds a
 * Fitness value from evaluation which can be sorted and compared.
 *
 */
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

	protected Genotype genotype() {
		return genotype;
	}
	
	protected Fitness fitness() {
		return fitness;
	}
	
	/**
	 * Set the system-wide object factory.
	 * 
	 * Failing to set this at the start of the program will result in a
	 * NullPointerException.
	 * 
	 * @param factory The system-wide object factory.
	 */
	public static void factory(Factory factory) {
		Individual.factory = factory;
	}

	/**
	 * Make an Individual with a given Genotype.
	 * 
	 * @param genotype The Individual's Genotype.
	 */
	public Individual(Genotype genotype) {
		this.genotype = genotype;
		this.fitness = null;
	}

	/**
	 * Evaluate the fitness of this individual in the provided Context.
	 * 
	 * NOTE: The implementation of this method must not mutate the Context. This may
	 * involve copying elements of the Context as evaluation occurs.
	 * 
	 * Sets the fitness of this individual for later sorting.
	 * 
	 * @param context Context in which to evaluate.
	 */
	public void evaluate(Context context) {
		fitness = context.evaluate(genotype);
	}

	// TODO Do we have the Factory for only these two methods?
	/**
	 * Perform the crossover operation on two Individuals' Genotypes.
	 * 
	 * @param other The other Individual involved in the crossover.
	 * @return The Individual resulting from the crossover.
	 */
	public Individual crossover(Individual other) {
		return factory.makeIndividual(genotype.crossover(other.genotype));
	}

	/**
	 * Perform the mutation operation on an Invidual's Genotype.
	 * 
	 * @return The Individual resulting from the mutation.
	 */
	public Individual mutate() {
		return factory.makeIndividual(genotype.mutate());
	}

	@Override
	public int compareTo(Individual that) {
		return fitness.compareTo(that.fitness);
	}
}
