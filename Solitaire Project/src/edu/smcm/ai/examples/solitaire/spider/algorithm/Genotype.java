package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.ai.genetic.algorithm.RandomBoolean;
import edu.smcm.ai.genetic.algorithm.RandomInteger;
import edu.smcm.ai.genetic.algorithm.RandomReal;

public class Genotype extends edu.smcm.ai.genetic.algorithm.Genotype {

	// TODO Remove or move lists of variables from Genotype.
	private static List<Variable> boolean_heuristic;
	private static List<Variable> integer_heuristic;
	private static List<Variable> real_heuristic;

	static {
		boolean_heuristic = new ArrayList<Variable>();
		integer_heuristic = new ArrayList<Variable>();
		real_heuristic = new ArrayList<Variable>();
		
		boolean_heuristic.add(new RandomBoolean());
		integer_heuristic.add(new RandomInteger());
		real_heuristic.add(new RandomReal());
		
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
	
	public void addGene(double weight, Variable variable) {
		super.addGene(weight, variable);
	}
	
	public String visualise(Position position) {
		String result;
		
		result = String.format("% 6.2f ", evaluate(position));
		for (Gene gene : genes()) {
			result = result + gene.visualise(position) + " "; 
		}
		
		return result;
	}

	public String visualisationTitle() {
		String result;
		
		result = "Value ";
		for (Gene gene : genes()) {
			result = result + gene.visualiseTitle();
		}
		
		return result;
	}
	
	
	public String title() {
		String result;
		
		result = "";
		for (Gene gene : genes()) {
			result = result + gene.title();
		}
		
		return result;
	}
		
	public void dumpCSV(PrintStream output) {
		for (Gene gene : genes()) {
			gene.dumpCSV(output);
		}
	}
	
	public String toString() {
		String result;
		
		result = "";
		for (Gene gene : genes()) {
			result = result + gene;
		}
		
		return result;
	}	
}
