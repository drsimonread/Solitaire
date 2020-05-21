/**
 * 
 */
package edu.smcm.ai.genetic.programming;

/**
 *
 */
public class And extends BinaryOperator {

	public And(Expression left, Expression right) {
		super(left,right);
	}
	
	public OperatorType type() {
		return OperatorType.Logic;
	}
	
	// TODO Can all these be cleaned up by using a first-class function in Java?
	
	@Override
	public Value evaluate(Context context) {
		Value left_value;
		Value right_value;
		Value result;
		
		left_value = left().evaluate(context);
		right_value = right().evaluate(context);
		
		if (left_value.type() != DataType.Boolean || left_value.type() != DataType.Boolean) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} else {
			result = new Boolean(((Boolean) left_value).value() && ((Boolean) right_value).value());
		} 
		
		return result;
	}
	
	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("AND", level);
	}
}
