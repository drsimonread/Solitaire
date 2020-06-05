/**
 * 
 */
package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Boolean;
import edu.smcm.ai.genetic.Context;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.TypeError;
import edu.smcm.ai.genetic.Value;

/**
 *
 */
public class Or extends BinaryOperator {

	public Or(Expression left, Expression right) {
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
			result = new Boolean(((Boolean) left_value).value() || ((Boolean) right_value).value());
		} 
		
		return result;
	}
	
	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("OR", level);
	}
}
