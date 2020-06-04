package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.programming.Boolean;

public class RandomBoolean extends Heuristic {
	
	public Value evaluate(Subcontext subcontext) {
		return new Boolean(random().nextBoolean());
	}
	
	@Override
	public String abbreviation() {
		return "RBool";
	}

	@Override
	public String fullName() {
		// TODO Auto-generated method stub
		return "Ramdom Boolean";
	}

}
