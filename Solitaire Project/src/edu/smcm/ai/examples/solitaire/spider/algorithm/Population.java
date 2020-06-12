package edu.smcm.ai.examples.solitaire.spider.algorithm;

public class Population extends edu.smcm.ai.genetic.Population<Individual> {
	
	public Population(Factory factory) {
		super(factory);
	}
	
	// TODO This will crash if the individuals have not been set up yet!
	public String title() {
		return individuals().get(0).title();
	}
}
