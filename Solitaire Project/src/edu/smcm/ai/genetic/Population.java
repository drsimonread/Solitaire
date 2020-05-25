package edu.smcm.ai.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The population that's going to be evolved.
 * 
 * The population consists of some number of Individuals. In each generation
 * they are ranked according to their performance in some Context, then some of
 * their members are selected for the next generation. In this model, some of
 * the best and worst members are preserved. THe rest of the population comes
 * from either crossover or mutation of their Genotype. Crossover and mutation
 * can either occur in the whole population of the last generation or only those
 * Individuals who have been selected to go onto the next generation.
 * 
 * The best individuals are preserved so that there is no back-sliding of the
 * population. The worst individuals are kept as they provide diversity in the
 * pool for future crossover.
 *
 * The division of the population into groups is stated as the number of
 * individuals as stating a proportion of an overall population for each groups
 * could lead to rounding errors causing the population to increase or decrease
 * in size over time.
 */
public class Population<I extends Individual> {

	/**
	 * A random number generator.
	 * 
	 * There should be only one for the entire system so that seeds can be used and
	 * errors found when they occur.
	 */
	private static Random random;

	/**
	 * The number of the fittest Individuals that will be selected for the next
	 * generation.
	 */
	private int fittest_population;

	/**
	 * The number of the weakest Individuals that will be selected for the next
	 * generation.
	 */
	private int weakest_population;

	/**
	 * The number of Individuals that will be created by crossover for the next
	 * generation.
	 */
	private int crossover_population;

	/**
	 * The number of Individuals that will be created by mutation for the next
	 * generation.
	 */
	private int mutation_population;

	/**
	 * A flag indicating whether crossover will be limited only to the fittest and
	 * weakest selected individuals or whether it will occur in the population as a
	 * whole.
	 */
	private boolean limited_crossover;

	/**
	 * A flag indicating whether mutation will be limited only to the fittest and
	 * weakest selected individuals or whether it will occur in the population as a
	 * whole.
	 */
	private boolean limited_mutation;

	/**
	 * The list of Individuals currently in the Population.
	 */
	private List<I> individuals;

	/**
	 * Set the random number generator used by this class.
	 * 
	 * Not setting a random number generator will cause a NullPointerException.
	 * 
	 * @param random the random number generator to be used.
	 */
	public static void random(Random random) {
		Population.random = random;
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
	 * Create a population of random Individuals
	 * 
	 * TODO Needs some way of creating random individuals.
	 */
	@SuppressWarnings("unchecked")
	public Population(Factory factory) {
		int population;

		// Set default values for various populations
		this.fittest_population = 100;
		this.weakest_population = 100;
		this.crossover_population = 790;
		this.mutation_population = 10;

		// Set a variable for the total population because it makes code prettier
		population = fittest_population + weakest_population + 
				crossover_population + mutation_population;

		// Create the ArrayList for the population
		// Choose ArrayList because we want to sort and reverse it a lot
		this.individuals = new ArrayList<I>(population);

		// Set default values for mutation and crossover policies
		this.limited_crossover = true;
		this.limited_mutation = true;

		// Actually create all the individuals in the population using the supplied Factory
		for (int count = 0; count < population; count++) {
			// TODO Do something about making a sensible error message if this cast fails?
			individuals.add((I) factory.makeIndividual());
		}
	}

	/**
	 * Allow access to individuals from extending classes.
	 * 
	 * This is mostly to allow extending classes to print the population as it
	 * evolves.
	 * 
	 * @return The individuals in the population.
	 */
	protected List<I> individuals() {
		return individuals;
	}

	/**
	 * Set the number of fittest members of the population selected.
	 * 
	 * @param fittest_population Size of the fittest population.
	 */
	public void fittest_population(int fittest_population) {
		this.fittest_population = fittest_population;
	}

	/**
	 * Set the number of weakest members of the population selected.
	 * 
	 * @param weakest_population Size of the weakest population.
	 */
	public void weakest_population(int weakest_population) {
		this.weakest_population = weakest_population;
	}

	/**
	 * Set the number of members of the population created by crossover.
	 * 
	 * @param crossover_population Size of the crossover population.
	 */
	public void crossover_population(int crossover_population) {
		this.crossover_population = crossover_population;
	}

	/**
	 * Set the number of members of the population created by mutation.
	 * 
	 * @param mutation_population Size of the mutation population.
	 */
	public void mutation_population(int mutation_population) {
		this.mutation_population = mutation_population;
	}

	/**
	 * Cause this Population to use limited crossover.
	 * 
	 * If limited crossover is used then crossover only occurs between the already
	 * selected fittest and worst populations, otherwise it occurs in the population
	 * of the last generation as a whole.
	 * 
	 * @param limited_crossover use limited crossover.
	 */
	public void limited_crossover(boolean limited_crossover) {
		this.limited_crossover = limited_crossover;
	}

	/**
	 * Cause this Population to use limited mutation.
	 * 
	 * If limited mutation is used then mutation only occurs to the already selected
	 * fittest and worst populations, otherwise it occurs in the population of the
	 * last generation as a whole.
	 * 
	 * @param limited_mutation use limited mutation.
	 */
	public void limited_mutation(boolean limited_mutation) {
		this.limited_mutation = limited_mutation;
	}

	/**
	 * Create a new generation of Individuals using the Context to evaluate them.
	 * 
	 * @param context the Context in which evaluation will take place.
	 */
	@SuppressWarnings("unchecked")
	public void generation(Context context) {
		List<I> next_generation; // The individuals in the next generation.
		I left; // The left member in a crossover.
		I right; // The right member in a crossover.

		// Generate the array to hold all the Individuals in the next generation.
		next_generation = new ArrayList<I>(individuals.size());

		// Evaluate all the individuals in this generation in the provided context.
		for (I individual : individuals) {
			individual.evaluate(context);
		}

		// Reserve the fittest members of the population
		Collections.sort(individuals);
		for (int count = 0; count < weakest_population; count++) {
			next_generation.add(individuals.get(count));
		}

		// Preserve the weakest members of the population
		Collections.reverse(individuals);
		for (int count = 0; count < fittest_population; count++) {
			next_generation.add(individuals.get(count));
		}

		// Crossover some of the population
		for (int count = 0; count < crossover_population; count++) {
			// TODO An individual can crossover with itself, but this is rare
			if (limited_crossover) {
				// Crossover only occurs in members already selected for the next generation
				// TODO Crossover can occur with an Individual that's already been subjected to
				// crossover!
				left = next_generation.get(random.nextInt(next_generation.size()));
				right = next_generation.get(random.nextInt(next_generation.size()));
			} else {
				// Crossover occurs between any members of the previous generation
				left = individuals.get(random.nextInt(individuals.size()));
				right = individuals.get(random.nextInt(individuals.size()));
			}

			// Actually perform the crossover
			// NOTE: The cast is necessary as crossover is only bound tp return something of
			// class Interface, not something of class I which extends Interface.
			// TODO Do something about making a sensible error message if this cast fails?
			next_generation.add((I) left.crossover(right));
		}

		// Mutate some of the population
		for (int count = 0; count < mutation_population; count++) {
			if (limited_mutation) {
				// Mutation only occurs in the population already selected
				// TODO This includes individuals that have already been subject to crossover of
				// mutation!
				left = next_generation.get(random.nextInt(next_generation.size()));
			} else {
				// Mutation occurs in any member of the previous population.
				left = individuals.get(random.nextInt(individuals.size()));
			}

			// Actually perform the mutation
			// NOTE: The cast is necessary as crossover is only bound tp return something of
			// class Interface, not something of class I which extends Interface.
			next_generation.add((I) left.mutate());
		}

		// Make the temporary population the next generation
		individuals = next_generation;
	} 
	
	// TODO Add an evolve method that iterates generation and creates a new Context (optionally)
	// Termination conditions?
}
