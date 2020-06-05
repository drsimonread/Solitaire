package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Boolean;
import edu.smcm.ai.genetic.Context;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Integer;
import edu.smcm.ai.genetic.Real;
import edu.smcm.ai.genetic.TypeError;
import edu.smcm.ai.genetic.Value;
import edu.smcm.util.ImplementationErrorException;

public class LessThan extends BinaryOperator {
	
	public LessThan(Expression left, Expression right) {
		super(left,right);
	}
	
	public OperatorType type() {
		return OperatorType.Relational;
	}
	
	@Override
	public Value evaluate(Context context) {
		Value left_value;
		Value right_value;
		Value result;
		
		left_value = left().evaluate(context);
		right_value = right().evaluate(context);
		
		if (left_value.type() != DataType.Real || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() != DataType.Real || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() == DataType.Real && right_value.type() == DataType.Real) {
			result = new Boolean(((Real) left_value).value() < ((Real) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Integer) {
			result = new Boolean(((Integer) left_value).value() < ((Integer) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Real) {
			result = new Boolean(((Integer) left_value).value() < ((Real) right_value).value());
		} else if (left_value.type() == DataType.Real && right_value.type() == DataType.Integer) {
			result = new Boolean(((Real) left_value).value() < ((Integer) right_value).value());
		} else {
			throw new ImplementationErrorException();
		}
		
		return result;
	}

	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("LESS THAN", level);
	}
}
