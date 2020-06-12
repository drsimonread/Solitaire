package edu.smcm.ai.examples.solitaire.spider.algorithm;

import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DealNewRow;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;

public class GeneticAlgorithm {

	private static final int generations;
	private static final Factory factory;
	
	static {
		generations = 100;
		factory = new Factory();
	}
	
	public static void main(String[] arguments) {
		Factory factory;
		Population population;
		Context context;
		
		// TODO Initialise all random number generators if necessary
		// TODO Initialise all static variables if necessary
		factory = new Factory();
		
		factory.experiment(0);
		
		factory.initialiseGenotype();
		
		// Create a genotype template
		population = new Population(factory);

		// TODO This evolves against a fixed set of games (context) for all generations.  This should be an option.
		// New set of games every iteration to prevent training to the games
		context = new Context();
		
		System.out.println(population.title());
		for (int generation = 0; generation < generations; generation++) {

			
			population.generation(context);	
			// TODO How do we capture/visualise evolution
		}
		
		// TODO I really don't care about the whole final population, do I?
		population.dumpCSV("population.csv");
		
	}	
}
