package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.programming.Integer;

public class RandomInteger extends Heuristic {
	
	public Value evaluate(Subcontext subcontext) {
		return new Integer(random().nextInt());
	}
	
	@Override
	public String abbreviation() {
		return "RInt";
	}

	@Override
	public String fullName() {
		// TODO Auto-generated method stub
		return "Ramdom Integer";
	}

}
