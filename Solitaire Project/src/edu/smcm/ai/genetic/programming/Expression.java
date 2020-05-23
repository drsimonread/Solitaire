package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Context;

public abstract class Expression {
	public abstract Value evaluate(Context context);
	public abstract OperatorType type();
	public abstract String prettyPrint(int level);
	
	// TODO There has to be a more efficient way of doing this
	protected String spaces(int number) {
		String result;
		
		result = "";
		for (int i = 0; i < number; i++) {
			result = result + " ";
		}
		
		return result;
	}
}
