package edu.smcm.ai.genetic.algorithm;

import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Position;
import edu.smcm.ai.genetic.programming.Integer;

public class RandomInteger extends Variable {
	
	public Value evaluate(Position position) {
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

	@Override
	public DataType dataType() {
		return DataType.Integer;
	}

}
