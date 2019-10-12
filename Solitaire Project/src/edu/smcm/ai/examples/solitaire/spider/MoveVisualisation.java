package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.ai.genetic.Genotype;

public class MoveVisualisation implements Comparable<MoveVisualisation> {
	private Move move;
	private Genotype genotype;
	private Game game;
	private double value; // Optimisation to prevent having to evaluate many times during sort
	
	public MoveVisualisation(Game game, Move move, Genotype genotype) {
		this.move = move;
		this.genotype = genotype;
		this.game = game;
		this.value = genotype.evaluate(game, move);
	}
	
	public Move move() {
		return move;
	}
	
	public String title () {
		return move.title() + " " + genotype.visualisationTitle(); 
	}
	
	public String visualise() {
		return move + " " + genotype.visualise(game, move);
	}
	
	
	@Override
	public int compareTo(MoveVisualisation that) {
		return Double.compare(value, ((MoveVisualisation) that).value);
	}
}
