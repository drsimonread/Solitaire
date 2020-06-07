package edu.smcm.ai.examples.solitaire.spider.algorithm;

public class Factory extends edu.smcm.ai.genetic.Factory {

	@Override
	public Individual makeIndividual() {
		return new Individual();
	}

	@Override
	public Individual makeIndividual(edu.smcm.ai.genetic.Genotype genotype) {
		// TODO Auto-generated method stub
		return new Individual(genotype);
	}

	@Override
	public Genotype makeGenotype() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Context makeContext() {
		// TODO Auto-generated method stub
		return null;
	}

}
