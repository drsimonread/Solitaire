package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.genetic.programming.Boolean;
import edu.smcm.ai.genetic.programming.Value;

public class DealNewRow extends edu.smcm.ai.genetic.algorithm.Heuristic {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position subcontext) {
		return new Boolean(true);
	}

	@Override
	public String abbreviation() {
		return "DNR";
	}

	@Override
	public String fullName() {
		return "Deal New Row";
	}
}
