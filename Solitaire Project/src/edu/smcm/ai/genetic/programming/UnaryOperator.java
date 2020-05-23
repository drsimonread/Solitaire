package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Context;

public abstract class UnaryOperator extends Expression {

	private Expression expression;
	
	public UnaryOperator(Expression expression) {
		this.expression = expression;
	}
	
	public Expression expression() {
		return expression;
	}
	
	public void expression(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public abstract Value evaluate(Context context);

	public String prettyPrintHelper(String keyword, int level) {
		return spaces(level) + "(" + keyword + "\n" + expression.prettyPrint(level + 1) +
				spaces(level) + ")\n";
	}
}
