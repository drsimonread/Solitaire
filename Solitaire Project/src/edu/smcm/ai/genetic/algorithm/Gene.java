package edu.smcm.ai.genetic.algorithm;

import java.util.Random;

import edu.smcm.ai.genetic.Variable;

/**
 * In Genetic Algorithms a Gene is simply a Heuristic for evaluating a Context
 * and a weight for that Gene.
 */
public class Gene {

	/**
	 * A random number generator.
	 */
	private static Random random;

	/**
	 * The weight of this Gene.
	 */
	private double weight;

	/**
	 * The heuristic associated with this Gene.
	 */
	private Variable heuristic;

	static {
		// Set a default random number generator
		Gene.random = new Random();
	}

	/**
	 * All-arguments constructor for a Gene.
	 * 
	 * @param weight    The initial weight of the Gene.
	 * @param heuristic The Heuristic associated with this Gene.
	 */
	public Gene(double weight, Variable heuristic) {
		this.weight = weight;
		this.heuristic = heuristic;
	}

	/**
	 * A constructor for a Gene that initialises a random weight.
	 * 
	 * @param heuristic The Heuristic associated with this Gene.
	 */
	public Gene(Variable heuristic) {
		this.weight = random.nextDouble();
		this.heuristic = heuristic;
	}

	/**
	 * A copy constructor for a Gene.
	 * 
	 * TODO Is this necessary?
	 * 
	 * @param that The Gene to be copied.
	 */
	public Gene(Gene that) {
		this.weight = that.weight;
		this.heuristic = that.heuristic;
	}

	/**
	 * Set the random number generator to be the system-wide one.
	 * 
	 * @param random The system-wide random number generator.
	 */
	public static void random(Random random) {
		Gene.random = random;
	}

	/**
	 * Allow access to random number generator in subclasses.
	 * 
	 * @return The random number generator.
	 */
	protected Random random() {
		return random;
	}

	/**
	 * Accessor for weight.
	 * 
	 * @return The weight associated with this Gene.
	 */
	public double weight() {
		return weight;
	}

	/**
	 * Mutator for weight.
	 * 
	 * @param weight The weight associated with this Gene.
	 */
	public void weight(double weight) {
		this.weight = weight;
	}

	/**
	 * Accessor for heuristic.
	 * 
	 * @return The heuristic associated with this Gene.
	 */
	public Variable heuristic() {
		return heuristic;
	}
	
	/**
	 * A method to cause mutation of a Gene.
	 */
	public void mutate() {
		this.weight = random.nextFloat();
	}
}
