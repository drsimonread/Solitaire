package edu.smcm.ai.genetic.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Genotype extends edu.smcm.ai.genetic.Genotype {

	private List<Gene> genes;
	
	public Genotype() {
		this.genes = new ArrayList<Gene>(); 
	}
	
	public Genotype(Genotype that) {
		this.genes = new ArrayList<Gene>();
		this.genes.addAll(that.genes);
	}
		
	public void addGene(Heuristic heuristic) {
		genes.add(new Gene(heuristic));
	}
	
	private void addGene(Gene gene) {
		genes.add(gene);
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
				result.addGene(genes.get(count));
			} else {
				result.addGene(t.genes.get(count));				
			}
		}
		
		return result;
	}
}
