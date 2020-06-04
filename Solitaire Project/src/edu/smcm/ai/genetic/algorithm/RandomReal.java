package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.Position;
import edu.smcm.ai.genetic.programming.Real;

public class RandomReal extends Heuristic {
	
	public Value evaluate(Position subcontext) {
		return new Real(random().nextInt());
	}
	
	@Override
	public String abbreviation() {
		return "RReal";
	}

	@Override
	public String fullName() {
		// TODO Auto-generated method stub
		return "Ramdom Real";
	}

}
