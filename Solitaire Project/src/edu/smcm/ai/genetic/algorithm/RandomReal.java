package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.Variable;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Position;
import edu.smcm.ai.genetic.Real;
import edu.smcm.ai.genetic.Value;

public class RandomReal extends Variable {
	
	public Value evaluate(Position position) {
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

	@Override
	public DataType dataType() {
		return DataType.Real;
	}

}
