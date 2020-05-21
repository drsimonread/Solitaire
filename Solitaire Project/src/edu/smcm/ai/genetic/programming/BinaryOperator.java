package edu.smcm.ai.genetic.programming;

public abstract class BinaryOperator extends Expression {
	private Expression left;
	private Expression right;
	
	public BinaryOperator(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	/* Need to be public to allow mutation and crossover */
	public Expression left() {
		return left;
	}
	
	public Expression right() {
		return right;
	}
	
	public void left(Expression left) {
		this.left = left;
	}
	
	public void right(Expression right) {
		this.right = right;
	}
	
	protected String prettyPrintHelper(String keyword, int level) {
		return spaces(level) + "(" + keyword + "\n" + 
				left().prettyPrint(level + 1) + 
				right().prettyPrint(level + 1) + 
				spaces(level) + ")\n";
	}
}
