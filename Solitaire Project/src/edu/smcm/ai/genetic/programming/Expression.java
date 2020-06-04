package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.Context;

public abstract class Expression implements PrettyPrintable {
	public abstract Value evaluate(Context context);
	public abstract OperatorType type();
	
	// TODO There has to be a more efficient way of creating a fixed number of spaces
	// TODO This belongs somehow to PrettyPrintable
	protected String spaces(int number) {
		String result;
		
		result = "";
		for (int i = 0; i < number; i++) {
			result = result + " ";
		}
		
		return result;
	}
}
