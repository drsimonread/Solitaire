package edu.smcm.ai.examples.solitaire.spider.variables;

import edu.smcm.ai.genetic.Boolean;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Value;

public class DealNewRow extends edu.smcm.ai.genetic.Variable {

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

	@Override
	public DataType dataType() {
		return DataType.Boolean;
	}
}