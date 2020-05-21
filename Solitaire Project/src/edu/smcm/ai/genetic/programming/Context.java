package edu.smcm.ai.genetic.programming;

import java.util.List;

public abstract class Context {

		public abstract List<Variable> booleanVariables();
		public abstract List<Variable> integerVariables();
		public abstract List<Variable> realVariables();
	
}
