package edu.smcm.ai.genetic;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Fitness;
import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Player;

public class Individual implements Comparable<Individual> {

	private static final Player player;
	
	private Fitness fitness;
	private Genotype genotype;
	private Random random;
	
	static {
		player = new Player();
	}
	
	public Individual(Genotype genotype, Random random) {
		this.fitness = new Fitness();
		this.genotype = new Genotype(genotype);
		this.random = random;
	}
	
	public void evaluateFitness(List<Game> games) {
		Game game_copy;
		
		fitness = new Fitness();
		for (Game game : games) {
			game_copy = new Game(game);
			fitness.update(player.play(game_copy, genotype));
		}
	}
	
	public Individual crossover(Individual other) {
		return new Individual(genotype.crossover(other.genotype), random);
	}

	public Individual mutate() {
		return new Individual(new Genotype(genotype).mutate(), random);
	}
	
	// TODO Make comparators for various fitness criteria and allow them to be used
	@Override
	public int compareTo(Individual that) {
		return Double.compare(fitness.scoreTwo(), ((Individual) that).fitness.scoreTwo());
	}
	
	public void dumpCSV(PrintStream output) {
		output.print(fitness + ", ");
		genotype.dumpCSV(output);
		output.println();
	}
	
	public String title() {
		return String.format("%1$-7s %2$-5s %3$-5s ", "Sun", "Coll", "Wins") + genotype.title();
	}
	
	public String toString() {
		return "" + String.format("%1$7.2f %2$4.3f %3$4.3f ", fitness.scoreTwo(), fitness.collected(), fitness.winRatio()) + genotype;
	}
}
