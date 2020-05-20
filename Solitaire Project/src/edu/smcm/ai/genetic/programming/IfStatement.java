/**
 * 
 */
package edu.smcm.ai.genetic.programming;

/**
 *
 */
public class IfStatement extends Expression {

	private Expression condition;
	private Expression true_operation;
	private Expression false_operation;
	
	public Expression condition() {
		return condition;
	}
	
	public Expression true_operation() {
		return true_operation;
	}
	
	public Expression false_operation() {
		return false_operation;
	}
	
	public void condition(Expression condition) {
		this.condition = condition;
	}
	
	public void true_operation(Expression true_operation) {
		this.true_operation = true_operation;
	}
	
	public void false_operation(Expression false_operation) {
		this.false_operation = false_operation;
	}
	
	public OperatorType type() {
		return OperatorType.Arithmetic;
	}
	
	@Override
	public Value evaluate(Context context) {
		Value condition_value;
		Value result;
		
		condition_value = condition.evaluate(context);
		if (condition_value.type() != DataType.Boolean) {
			// TODO Meaningful message
			throw new TypeError();
		} else if (((Boolean) condition_value).value()) {
			result = true_operation.evaluate(context);
		} else {
			result = false_operation.evaluate(context);
		}
		
		return result;
	}

}
