package edu.smcm.ai.genetic;

public abstract class Context {
				
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
