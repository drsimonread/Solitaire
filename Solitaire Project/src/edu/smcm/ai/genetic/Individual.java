package edu.smcm.ai.genetic;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Fitness;
import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Player;

public abstract class Individual implements Comparable<Individual> {

	/**
	 * The central random number generator to be used in all classes.
	 */
	private static Random random;

	private Fitness fitness;
	private Genotype genotype;

	public static void random(Random random) {
		Individual.random = random;
	}

	/**
	 * Extending classes must implement a constructor creating an Individual with a random Genotype.
	 */
	
	/**
	 * Evaluate the fitness of this individual in the provided Context.
	 * 
	 * NOTE: The implementation of this method might mutate the Context.  If this might be a 
	 * problem then the Context should be copied first.
	 * 
	 * Sets the fitness of this individual for later sorting.
	 * 
	 * @param context Context in which to evaluate.
	 */
	public abstract void evaluate(Context context);

	public abstract Individual crossover(Individual other);
//		return new Individual(genotype.crossover(other.genotype), random);

	public abstract Individual mutate();
//		return new Individual(new Genotype(genotype).mutate(), random);

	// TODO Make comparators for various fitness criteria and allow them to be used
	@Override
	public abstract int compareTo(Individual that);
// TODO Can't use the following for some reason
//		return fitness.compareTo(that.fitness);
}
