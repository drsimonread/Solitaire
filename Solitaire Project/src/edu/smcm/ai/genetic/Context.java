package edu.smcm.ai.genetic;

import java.util.List;

import edu.smcm.ai.genetic.programming.Variable;

public abstract class Context {

		public abstract List<Variable> booleanVariables();
		public abstract List<Variable> integerVariables();
		public abstract List<Variable> realVariables();
	
		public abstract Context clone();
}
