package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Fitness;
import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Player;

public class Individual extends edu.smcm.ai.genetic.Individual {

	private static final Player player;

	static {
		player = new Player();
	}
	
	public Individual(Genotype genotype, Random random) {
		this.fitness = new Fitness();
		this.genotype = new Genotype(genotype);
	}
	
	public void evaluateFitness(List<Game> games) {
		Game game_copy;
		
		fitness = new Fitness();
		for (Game game : games) {
			game_copy = new Game(game);
			fitness.update(player.play(game_copy, genotype));
		}
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
