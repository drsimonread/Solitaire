/**
 * 
 */
package edu.smcm.ai.genetic.programming;

import edu.smcm.util.ImplementationErrorException;

/**
 *
 */
public class Division extends BinaryOperator {

	public Division(Expression left, Expression right) {
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
		
		if (left_value.type() != DataType.Double || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() != DataType.Double || left_value.type() != DataType.Integer) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Double) {
			result = new Double(((Double) left_value).value() / ((Double) right_value).value());
		} else if (left_value.type() == DataType.Integer && right_value.type() == DataType.Integer) {
			result = new Integer(((Integer) left_value).value() / ((Integer) right_value).value());
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Double) {
			result = new Double(((Integer) left_value).value() / ((Double) right_value).value());
		} else if (left_value.type() == DataType.Double && right_value.type() == DataType.Integer) {
			result = new Double(((Double) left_value).value() / ((Integer) right_value).value());
		} else {
			throw new ImplementationErrorException();
		}
		
		return result;
	}
}
