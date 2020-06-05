package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.io.PrintStream;

import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;

public class Gene extends edu.smcm.ai.genetic.algorithm.Gene {

	/**
	 * All-arguments constructor for a Gene.
	 * 
	 * @param weight    The initial weight of the Gene.
	 * @param heuristic The Heuristic associated with this Gene.
	 */
	public Gene(double weight, Variable variable) {
		super(weight, variable);
	}

	/**
	 * A constructor for a Gene that initialises a random weight.
	 * 
	 * @param heuristic The Heuristic associated with this Gene.
	 */
	public Gene(Variable variable) {
		super(variable);
	}

	/**
	 * A copy constructor for a Gene.
	 * 
	 * TODO Is this necessary?
	 * 
	 * @param that The Gene to be copied.
	 */
	public Gene(Gene that) {
		super(that);
	}

	public String visualise(Position position) {
		Value value;
		
		value = variable().evaluate(position);
		
		return String.format("%1$ 4.2f*%2$ 3d=%3$ 5.2f", weight(), value, weight() * value.toDouble());
	}
	
	public String visualiseTitle() {
		return String.format("%1$-16s", variable().abbreviation());
	}
	
	public String title() {
		return String.format("%1$-12s", variable().abbreviation());
	}
	
	public void dumpCSV(PrintStream output) {
		output.print(weight() + ", ");
	}
	
	public String toString() {
		return String.format("%1$ 9.8f ", weight());
	}
}
