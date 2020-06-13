package edu.smcm.ai.examples.solitaire.spider.algorithm;

import edu.smcm.ai.genetic.Variable;

public class Genotype extends edu.smcm.ai.genetic.algorithm.Genotype {

	public Genotype() {
		super();
	}
	
	public Genotype(Genotype that) {
		super(that);
	}
	
	// Needs to be public for Visualise
	@Override
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
			result = result + gene.title() + ", ";
		}
		
		return result;
	}

	public String toString() {
		String result;
		
		result = "";
		for (Gene gene : genes()) {
			result = result + gene + ", ";
		}
		
		return result;
	}	
}
