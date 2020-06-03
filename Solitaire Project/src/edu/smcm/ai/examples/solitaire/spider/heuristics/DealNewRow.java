package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Move;

public class DealNewRow extends Heuristic {

	public DealNewRow() {
		super(99, 1, 1);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public String toString() {
		return "99  1  1";
	}
	
}
