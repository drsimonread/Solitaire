package edu.smcm.ai.genetic.programming;

import edu.smcm.util.ImplementationErrorException;

public class Equals extends BinaryOperator {

	public Equals(Expression left, Expression right) {
		super(left,right);
	}

	public OperatorType type() {
		return OperatorType.Relational;
	}
	
	// TODO I'd like to restrict this to Integers only but that will make crossover and mutation hard.1q
	
	@Override
	public Value evaluate(Context context) {
		Value left_value;
		Value right_value;
		Value result;
		
		left_value = left().evaluate(context);
		right_value = right().evaluate(context);
		
		if (left_value.type() != DataType.Double || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() != DataType.Double || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Double) {
			result = new Boolean(((Double) left_value).value() == ((Double) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Integer) {
			result = new Boolean(((Integer) left_value).value() == ((Integer) right_value).value());
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Double) {
			result = new Boolean(((Integer) left_value).value() == ((Double) right_value).value());
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Integer) {
			result = new Boolean(((Double) left_value).value() == ((Integer) right_value).value());
		} else {
			throw new ImplementationErrorException();
		}
		
		return result;
	}
}
