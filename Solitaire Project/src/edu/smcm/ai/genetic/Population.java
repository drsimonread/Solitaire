package edu.smcm.ai.genetic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
	private static Random random;
	
	private static final int population;
	private static final int fittest_population;
	private static final int weakest_population;
	private static final int crossover_population;
	private static final int mutation_population;

	private static boolean limited_crossover;
	private static boolean limited_mutation;

	private List<Individual> individuals;

	// Populations stated rather than proportions as proportions are rounded causing the population to grow or shrick over time (potentially).
	static {
		// TODO Population size is set elsewhere too!
		fittest_population = 100;
		weakest_population = 100;
		crossover_population = 790;
		mutation_population = 10;
		population = fittest_population + weakest_population + crossover_population + crossover_population;
	}

	public static void random(Random random) {
		Population.random = random;
	}
	
	public Population() {
		this.individuals = new ArrayList<Individual>(population);
//		for (int count = 0; count < population; count++) {
//			individuals.add(new Individual());
//		}
	}
	
	public void add(Individual individual) {
		individuals.add(individual);
	}

	public static void crossover(boolean limited_crossover) {
		Population.limited_crossover = limited_crossover;
	}

	public static void mutation(boolean limited_mutation) {
		Population.limited_mutation = limited_mutation;
	}

	public void generation(Context context) {
		List<Individual> next_generation;
		Individual left;
		Individual right;

		next_generation = new ArrayList<Individual>(individuals.size());

		for (Individual individual : individuals) {
			individual.evaluate(context);
		}

		// Reserve the fittest
		Collections.sort(individuals);
		for (int count = 0; count < weakest_population; count++) {
			next_generation.add(individuals.get(count));
		}

		// Preserve some of the weakest for diversity
		Collections.reverse(individuals);
		for (int count = 0; count < fittest_population; count++) {
			next_generation.add(individuals.get(count));
		}

		// Crossover some of the population so far
		for (int count = 0; count < crossover_population; count++) {
			// TODO An individual can crossover with itself, but this is rare
			if (limited_crossover) {
				left = next_generation.get(random.nextInt(next_generation.size()));
				right = next_generation.get(random.nextInt(next_generation.size()));
			} else {
				left = individuals.get(random.nextInt(individuals.size()));
				right = individuals.get(random.nextInt(individuals.size()));
			}

			next_generation.add(left.crossover(right));
		}

		// Mutate some of the of the population so far
		for (int count = 0; count < mutation_population; count++) {
			if (limited_mutation) {
				left = next_generation.get(random.nextInt(next_generation.size()));
			} else {
				left = individuals.get(random.nextInt(individuals.size()));
			}

			next_generation.add(left.mutate());
		}

		individuals = next_generation;
	}
}
