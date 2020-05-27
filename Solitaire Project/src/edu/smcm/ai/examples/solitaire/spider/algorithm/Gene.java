package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;
import java.util.Random;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.MoveStack;
import edu.smcm.ai.examples.solitaire.spider.heuristics.DealNewRow;
import edu.smcm.ai.genetic.algorithm.Heuristic;

public class Gene {

	private double weight;
	private Heuristic heuristic;
	
	// TODO Make this static and set externally at the start of the run
	private Random random;
	
	public Gene(double weight, Heuristic heuristic, Random random) {
		this.weight = weight;
		this.heuristic = heuristic;
		this.random = random;
	}
	
	public Gene(Gene that) {
		this.weight = that.weight;
		this.heuristic = that.heuristic;
		this.random = that.random;
	}
	
	public double evaluate(Game game, Move move) {
		double result;
		
		if (move instanceof MoveStack) {
			result = weight * heuristic.value(game, (MoveStack) move);
		} else {
			result = weight * heuristic.value(game, (DealNewRow) move);
		}
		
		return result;
	}
	
	public String visualise(Game game, Move move) {
		int value;
		
		if (move instanceof MoveStack) {
			value = heuristic.value(game, (MoveStack) move);
		} else {
			value = heuristic.value(game, (DealNewRow) move);
		}

		return String.format("%1$ 4.2f*%2$ 3d=%3$ 5.2f", weight, value, weight * value);
	}
	
	public String visualiseTitle() {
		return String.format("%1$-16s", heuristic.abbreviation());
	}
	
	public String title() {
		return String.format("%1$-12s", heuristic.abbreviation());
	}
	
	public Gene mutate() {
		Gene result;
		
		result = new Gene(this);
		result.weight = random.nextDouble();
		
		return result;
	}

	public void dumpCSV(PrintStream output) {
		output.print(weight + ", ");
	}
	
	public String toString() {
		return String.format("%1$ 9.8f ", weight);
	}
}
