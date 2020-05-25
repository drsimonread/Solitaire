package edu.smcm.ai.genetic;

import java.util.Random;

/**
 * A class to make other classes.
 * 
 * This Factory class is necessary because we cannot call the constructor of a
 * subclass in the definition of a superclass. In part because we don't know it
 * exists (constructors are not inherited) and in part because the
 * compiler/runtime environment might have no idea which constructor to call.
 * 
 * Instead, we provide an object that makes objects of all the subclasses. This
 * must be an object, unlike a traditional Factory, as we can't refer to a
 * static method of a subclass for the same reasons we can't refer to the
 * constructors of subclasses.
 * 
 * Every example that uses this generic structure will need to define a Factory.
 * 
 * We have chosen to put all the different classes Factories into one class to
 * reduce the number of classes that must be implemented to use this system.
 *
 */
public abstract class Factory {

	/**
	 * The system-wide random number generator.
	 */
	private static Random random;

	/**
	 * Set the random number generator to be used by this factory.
	 * 
	 * @param random The random number generator to be used.
	 */
	public static void random(Random random) {
		Factory.random = random;
	}
	
	/**
	 * Allow subclasses to access random number generator.
	 * 
	 * @return The random number generator.
	 */
	protected Random random() {
		return random;
	}

	/**
	 * Make a radomised Individual for the population.
	 * 
	 * @return A new random Individual.
	 */
	public abstract Individual makeIndividual();

	/**
	 * Make an Individual with the given Genotype.
	 * 
	 * @return A new Individual with the given Genotype.
	 */
	public abstract Individual makeIndividual(Genotype genotype);

	/**
	 * Make a radomised Genotype for an Individual.
	 * 
	 * @return A new random Genotype for Individual.
	 */
	public abstract Genotype makeGenotype();

	/**
	 * Make a radomised Context in which to evaluate a population.
	 * 
	 * @return A new random Context.
	 */
	public abstract Context makeContext();

	
}
