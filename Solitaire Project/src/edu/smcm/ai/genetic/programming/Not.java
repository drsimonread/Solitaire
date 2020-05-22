/**
 * 
 */
package edu.smcm.ai.genetic.programming;

/**
 *
 */
public class Not extends UnaryOperator {

	public Not(Expression expression) {
		super(expression);
	}
	
	public OperatorType type() {
		return OperatorType.Logic;
	}
	
	@Override
	public Value evaluate(Context context) {
		Value result;
		
		result = expression().evaluate(context);
	
		if (result.type() != DataType.Boolean) {
			// TODO Meaningful Error Message
			throw new TypeError();
		} 
		
		return result;
	}
	
	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("NOT", level);
	}
}