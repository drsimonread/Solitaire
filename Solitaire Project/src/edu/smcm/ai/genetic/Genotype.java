package edu.smcm.ai.genetic;

import java.util.Random;

public abstract class Genotype {

	@SuppressWarnings("unused")
	private static Random random;
	
	public static void random(Random random) {
		Genotype.random = random;
	}
	
	/**
	 * Extending classes must implement a constructor creating a random Genotype.
	 */
	
	/**
	 * Evaluate this Genotype in the Context.
	 * 
	 * @param context Context of evaluation.
	 * @return fitness of Genotype in Context.
	 */
	public abstract double evaluate(Context context);
	
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
	public abstract Genotype crossover(Genotype that);
}
