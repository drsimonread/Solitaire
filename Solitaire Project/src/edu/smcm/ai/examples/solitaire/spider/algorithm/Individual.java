package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;

import edu.smcm.ai.examples.solitaire.spider.Fitness;

public class Individual extends edu.smcm.ai.genetic.Individual {
	
	public Individual(Genotype genotype) {
		super(genotype);
	}
	
	public void dumpCSV(PrintStream output) {
		output.print(fitness() + ", ");
		((Genotype) genotype()).dumpCSV(output);
		output.println();
	}
	
	public String title() {
		return String.format("%1$-7s %2$-5s %3$-5s ", "Sun", "Coll", "Wins") + ((Genotype) genotype()).title();
	}
	
	public String toString() {
		Fitness fitness;
		
		fitness = ((Fitness) fitness());
		return "" + String.format("%1$7.2f %2$4.3f %3$4.3f ", fitness.scoreTwo(), 
				fitness.collected(), fitness.winRatio()) + genotype();
	}
}
