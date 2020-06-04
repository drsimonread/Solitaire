package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Context;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.util.ImplementationErrorException;

public class Addition extends BinaryOperator {

	public Addition(Expression left, Expression right) {
		super(left,right);
	}
	
	public OperatorType type() {
		return OperatorType.Arithmetic;
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
			result = new Real(((Real) left_value).value() * ((Real) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Integer) {
			result = new Integer(((Integer) left_value).value() * ((Integer) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Real) {
			result = new Real(((Integer) left_value).value() * ((Real) right_value).value());
		} else if (left_value.type() == DataType.Real && right_value.type() == DataType.Integer) {
			result = new Real(((Real) left_value).value() * ((Integer) right_value).value());
		} else {
			throw new ImplementationErrorException();
		}
		
		return result;
	}

	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("ADD", level);
	}
}
