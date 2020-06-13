package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class GeneticAlgorithm {

	private static final int generations;

	static {
		generations = 100;
	}

	public static void main(String[] arguments) {
		Factory factory;
		Population population;
		PrintStream best_stream;
		PrintStream final_stream;
		Random random;
		int seed;
		
		// TODO Read seed in from keyboard or file
		seed = 45678;
		
		random = new Random(seed);

		// Initialise all random number generators if necessary
		edu.smcm.ai.genetic.Factory.random(random);
		edu.smcm.ai.genetic.Genotype.random(random);
		edu.smcm.ai.genetic.Individual.random(random);
		edu.smcm.ai.genetic.Population.random(random);
		edu.smcm.ai.genetic.Variable.random(random);
		edu.smcm.ai.genetic.algorithm.Gene.random(random);
		
		// TODO Initialise all static variables if necessary
		factory = new Factory();
		
		edu.smcm.ai.genetic.Individual.factory(factory);
		

		factory.experiment(0);

		factory.initialiseGenotype();

		population = new Population(factory);
		try {
			best_stream = new PrintStream(new File("best.csv"));
			final_stream = new PrintStream(new File("final.csv"));

			population.evolve(factory, generations, true, best_stream, final_stream);
		} catch (IOException caught) {
			System.err.println(caught);
			caught.printStackTrace();
		}
	}
}
