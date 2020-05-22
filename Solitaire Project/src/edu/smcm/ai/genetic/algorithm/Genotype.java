package edu.smcm.ai.genetic.algorithm;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Heuristic;
import edu.smcm.ai.examples.solitaire.spider.Move;

public class Genotype {

	private List<Gene> genes;
	private Random random;
	
	public Genotype(Random random) {
		this.genes = new ArrayList<Gene>(); 
		this.random = random;
	}
	
	public Genotype(Genotype that) {
		this.genes = new ArrayList<Gene>();
		this.genes.addAll(that.genes);
		this.random = that.random;
	}
	
	
	public Genotype newWeights() {
		Genotype result;
		
		result = new Genotype(this);
		
		for (Gene gene : genes) {
			gene.mutate();
		}
		
		return result;
	}
	
	public void addGene(Heuristic heuristic) {
		genes.add(new Gene(random.nextDouble(), heuristic, random));
	}
	
	public void addGene(double weight, Heuristic heuristic) {
		genes.add(new Gene(weight, heuristic, random));
	}
	
	public void addGene(Gene gene) {
		genes.add(gene);
	}
	
	public double evaluate(Game game, Move move) {
		double result;
		
		result = 0.0;
		 for (Gene gene : genes) {
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
	
	public Genotype mutate() {
		Genotype result;
		int position;
		Gene gene;
		
		result = new Genotype(this);
		
		position = random.nextInt(genes.size());
		
		gene = new Gene(result.genes.get(position));
		
		result.genes.set(position, gene.mutate());
		
		return result;
	}
	
	// TODO Two types of crossover -- single and random
	public Genotype crossover(Genotype that) {
		Genotype result;
		int position;
		
		position = random.nextInt(genes.size());
		result = new Genotype(random);
		
		for (int count = 0; count < genes.size(); count++) {
			if (count < position) {
				result.addGene(genes.get(count));
			} else {
				result.addGene(that.genes.get(count));				
			}
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
