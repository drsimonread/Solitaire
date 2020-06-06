package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DealNewRowRecogniser;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;
import edu.smcm.ai.genetic.algorithm.Genotype;
import edu.smcm.ai.genetic.algorithm.Individual;
import edu.smcm.ai.genetic.algorithm.Population;

public class GeneticAlgorithm {

	private static final int generations;
	private static final int population_size;
	private static final int number_of_games;
	private static Random random;
	
	static {
		generations = 100;
		population_size = 1000;
		number_of_games = 100;
		random = new Random();
	}
	
	public static void main(String[] arguments) {
		Population population;
		List<Game> games;
		Genotype template;

		Population.crossover(true);
		Population.mutation(true);
		
		// Create a genotype template
		// TODO The order of these matters due to crossover, but we don't know how
		template = new Genotype(random);
		template.addGene(new CreatedEmptyStack());
		template.addGene(new DealNewRowRecogniser());
		template.addGene(new DirtyFlush());
		template.addGene(new Discovery());
		template.addGene(new FillsEmptyStack());
		template.addGene(new NumberOfCards());
		template.addGene(new OpensMoveStackDestination());
		template.addGene(new StraightFlush());
		template.addGene(new TopMoved());

		population = new Population(random);
		for (int count = 0; count < population_size; count++) {
			population.add(new Individual(template.newWeights(), random));
		}

		// New set of games every iteration to prevent training to the games
		games = new ArrayList<Game>(number_of_games);
		for (int count = 0; count < number_of_games; count++) {
			games.add(new Game(2, true));
		}
		
		System.out.println(population.title());
		for (int generation = 0; generation < generations; generation++) {

			
			population.generation(games);	
		}
		
		population.dumpCSV("population.csv");
		
	}
	
}
