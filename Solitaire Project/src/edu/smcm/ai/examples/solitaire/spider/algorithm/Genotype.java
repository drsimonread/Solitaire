package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.heuristics.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.heuristics.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.heuristics.Discovery;
import edu.smcm.ai.examples.solitaire.spider.heuristics.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.heuristics.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.heuristics.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.heuristics.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.heuristics.TopMoved;
import edu.smcm.ai.genetic.algorithm.Heuristic;

public class Genotype extends edu.smcm.ai.genetic.algorithm.Genotype {
	// TODO These lists appear in two places now... Genotype and Context
	private static List<Heuristic> boolean_heuristic;
	private static List<Heuristic> integer_heuristic;
	private static List<Heuristic> real_heuristic;

	static {
		boolean_heuristic = new ArrayList<Heuristic>();
		integer_heuristic = new ArrayList<Heuristic>();
		real_heuristic = new ArrayList<Heuristic>();
		
		boolean_heuristic.add(new RandomBoolean());
		integer_heuristic.add(new RandomInteger());
		real_heuristic.add(new RandomDouble());
		
		integer_heuristic.add(new StraightFlush());
		integer_heuristic.add(new DirtyFlush());
		integer_heuristic.add(new NumberOfCards());
		integer_heuristic.add(new TopMoved());
		
		boolean_heuristic.add(new Discovery());
		boolean_heuristic.add(new OpensMoveStackDestination());
		boolean_heuristic.add(new FillsEmptyStack());
		boolean_heuristic.add(new CreatedEmptyStack());

	}

	
	public Genotype() {
		super();
	}
	
	public Genotype(Genotype that) {
		super(that);
	}
	
	public double evaluate(Game game, Move move) {
		double result;
		
		result = 0.0;
		 for (Gene gene : genes()) {
			 result = result + gene.evaluate(game, move);
		 }
		 
		 return result;
	}
	
	public String visualise(Game game, Move move) {
		String result;
		
		result = String.format("% 6.2f ", evaluate(game, move));
		for (Gene gene : genes) {
			result = result + gene.visualise(game, move) + " "; 
		}
		
		return result;
	}

	public String visualisationTitle() {
		String result;
		
		result = "Value ";
		for (Gene gene : genes) {
			result = result + gene.visualiseTitle();
		}
		
		return result;
	}
	
	
	public String title() {
		String result;
		
		result = "";
		for (Gene gene : genes) {
			result = result + gene.title();
		}
		
		return result;
	}
		
	public void dumpCSV(PrintStream output) {
		for (Gene gene : genes) {
			gene.dumpCSV(output);
		}
	}
	
	public String toString() {
		String result;
		
		result = "";
		for (Gene gene : genes) {
			result = result + gene;
		}
		
		return result;
	}	
}