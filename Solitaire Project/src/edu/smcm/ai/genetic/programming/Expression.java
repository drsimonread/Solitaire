package edu.smcm.ai.genetic.programming;

public abstract class Expression {
	public abstract Value evaluate(Context context);
	public abstract OperatorType type();
}
