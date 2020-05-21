/**
 * 
 */
package edu.smcm.ai.genetic.programming;

/**
 *
 */
public class IfStatement extends Expression {

	private Expression condition;
	private Expression true_expression;
	private Expression false_expression;
	
	public IfStatement(Expression condition, Expression true_expression, Expression false_expression) {
		this.condition = condition;
		this.true_expression = true_expression;
		this.false_expression = false_expression;
	}
	
	public Expression condition() {
		return condition;
	}
	
	public Expression true_operation() {
		return true_expression;
	}
	
	public Expression false_operation() {
		return false_expression;
	}
	
	public void condition(Expression condition) {
		this.condition = condition;
	}
	
	public void true_operation(Expression true_operation) {
		this.true_expression = true_operation;
	}
	
	public void false_operation(Expression false_operation) {
		this.false_expression = false_operation;
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
			result = true_expression.evaluate(context);
		} else {
			result = false_expression.evaluate(context);
		}
		
		return result;
	}

	@Override
	public String prettyPrint(int level) {
		return spaces(level) + "(IF\n" +
				condition.prettyPrint(level) +
				true_expression.prettyPrint(level) +
				false_expression.prettyPrint(level) +
				spaces(level) + ")\n";
	}
}
