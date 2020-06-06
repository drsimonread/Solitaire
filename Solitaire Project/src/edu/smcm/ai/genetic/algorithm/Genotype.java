package edu.smcm.ai.genetic.algorithm;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.examples.solitaire.spider.algorithm.Gene;
import edu.smcm.ai.genetic.Variable;

public class Genotype extends edu.smcm.ai.genetic.Genotype {

	private List<Gene> genes;
	
	public Genotype() {
		this.genes = new ArrayList<Gene>(); 
	}
	
	public Genotype(Genotype that) {
		this.genes = new ArrayList<Gene>();
		this.genes.addAll(that.genes);
	}
		
	public void addGene(Variable heuristic) {
		genes.add(new Gene(heuristic));
	}
	
	private void addGene(Gene gene) {
		genes.add(gene);
	}
	
	
	/**
	 * Used when visualising the genome for experimentation.
	 * 
	 * @param weight
	 * @param variable
	 */
	protected void addGene(double weight, Variable variable) {
		genes.add(new Gene(weight, variable));
	}
	
	// TODO Should we take a copy of genes first in genes()?
	// TODO Should we provide an iterator instead of genes()?
	// TODO Why does any other (non-extending) class need genes() anyway?
	public List<Gene> genes() {
		return genes;
	}
	
	@Override
	public Genotype mutate() {
		Genotype result;
		int position;
		
		result = new Genotype(this);
		
		position = random().nextInt(genes.size());
		
		result.genes.get(position).mutate();
		
		return result;
	}
	
	// TODO Two types of crossover -- single and random
	@Override
	public Genotype crossover(edu.smcm.ai.genetic.Genotype that) {
		Genotype result;
		int position;
		Genotype t;
		
		t = (Genotype) that;
		
		position = random().nextInt(genes.size());
		result = new Genotype();
		
		for (int count = 0; count < genes.size(); count++) {
			if (count < position) {
				result.addGene(new Gene(genes.get(count)));
			} else {
				result.addGene(new Gene(t.genes.get(count)));				
			}
		}
		
		return result;
	}
	
	public double evaluate(edu.smcm.ai.genetic.Position position) {
		double result;
		
		result = 0.0;
		 for (Gene gene : genes()) {
			 result = result + gene.evaluate(position);
		 }
		 
		 return result;
	}
}
