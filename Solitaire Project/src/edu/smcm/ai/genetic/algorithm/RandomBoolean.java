package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Position;
import edu.smcm.ai.genetic.programming.Boolean;

public class RandomBoolean extends Variable {
	
	public Value evaluate(Position position) {
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

	@Override
	public DataType dataType() {
		return DataType.Boolean;
	}

}
