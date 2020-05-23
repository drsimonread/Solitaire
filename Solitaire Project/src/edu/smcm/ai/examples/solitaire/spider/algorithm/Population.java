package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;

public class Population {
	private static final int population_size;
	private static final double fittest_proportion;
	private static final double weakest_proportion;
	private static final double crossover_proportion;
	private static final double mutation_proportion;

	private static boolean limited_crossover;
	private static boolean limited_mutation;

	private List<Individual> individuals;
	private Random random;

	static {
		// TODO Population size is set elsewhere too!
		population_size = 1000;
		fittest_proportion = 0.10;
		weakest_proportion = 0.10;
		crossover_proportion = 0.79;
		mutation_proportion = 0.01;

		// I'm not sure this assert would really work, it's here for documentation
		assert (1.0 == fittest_proportion + weakest_proportion + crossover_proportion + mutation_proportion);
	}

	public Population(Random random) {
		this.individuals = new ArrayList<Individual>(population_size);
		this.random = random;
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

	public void generation(List<Game> games) {
		List<Individual> next_generation;
		Individual left;
		Individual right;

		next_generation = new ArrayList<Individual>(individuals.size());

		for (Individual individual : individuals) {
			individual.evaluateFitness(games);
		}

		// Reserve the fittest
		Collections.sort(individuals);
		System.out.println(individuals.get(individuals.size() - 1));
		for (int count = 0; count < population_size * weakest_proportion; count++) {
			next_generation.add(individuals.get(count));
		}

		// Preserve some of the weakest for diversity
		Collections.reverse(individuals);
		for (int count = 0; count < population_size * fittest_proportion; count++) {
			next_generation.add(individuals.get(count));
		}

		// Crossover some of the population so far
		for (int count = 0; count < population_size * crossover_proportion; count++) {
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
		for (int count = 0; count < population_size * crossover_proportion; count++) {
			if (limited_mutation) {
				left = next_generation.get(random.nextInt(next_generation.size()));
			} else {
				left = individuals.get(random.nextInt(individuals.size()));
			}

			next_generation.add(left.mutate());
		}

		individuals = next_generation;
	}

	public void dumpCSV(String file_name) {
		PrintStream output;
		
		try {
			output = new PrintStream(new File(file_name));
			
			for (Individual individual : individuals) {
				individual.dumpCSV(output);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not create or write to " + file_name + ".");
		}
	}

	// TODO This will crash if the individuals have not been set up yet!
	public String title() {
		return individuals.get(0).title();
	}
}
