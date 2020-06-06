package edu.smcm.ai.examples.solitaire.spider;

import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Genotype;

public class MoveVisualisation implements Comparable<MoveVisualisation> {
	private Position position;
	private Genotype genotype;
	private double value; // Optimisation to prevent having to evaluate many times during sort
	
	public MoveVisualisation(Position position, Genotype genotype) {
		this.position = position;
		this.genotype = genotype;
		this.value = genotype.evaluate(position);
	}
	
	public Position position() {
		return position;
	}
	
	public String title () {
		return position.move().title() + " " + genotype.visualisationTitle(); 
	}
	
	public String visualise() {
		return position + " " + genotype.visualise(position);
	}
	
	
	@Override
	public int compareTo(MoveVisualisation that) {
		return Double.compare(value, ((MoveVisualisation) that).value);
	}
}
