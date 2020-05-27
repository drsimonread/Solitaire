package edu.smcm.ai.genetic;

import java.util.List;

import edu.smcm.ai.genetic.programming.Variable;

// TODO Does this class need to be Cloneable?
public abstract class Context implements Cloneable {

		// TODO Should these just be part of the Genotype for Programming?
		public abstract List<Variable> booleanVariables();
		public abstract List<Variable> integerVariables();
		public abstract List<Variable> realVariables();
		
		public abstract Context clone();
		
		/**
		 * Evaluate an Individual's Genotype within a Context to get its Fitness.
		 * 
		 * NOTE: This method may need to duplicate the internals of the Context before evaluation so 
		 * that the Context can be used for future Individuals.
		 * 
		 * @param genotype The genotype to be evaluated.
		 * @return The Fitness of the Genotype in the Context.
		 */
		public abstract Fitness evaluate(Genotype genotype);
}
