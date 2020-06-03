/**
 * A Population represents the collection of Individuals that will be evolved.
 * It is generalised in several ways.
 * 
 * TODO Add description of generalisations in Population.
 * 
 * Each Individual has Genotype and is evaluated in some Context to derive a
 * Fitness. The definition of the Context, Fitness and Genotype are left to the
 * implementation.
 * 
 * There is no method to cause the evolution of Populations over several
 * generations.
 * 
 * TODO Add a method to control evolution over several generations.
 * 
 * This should probably be a separate class and decide if a new Context should
 * be created for each individual, for each generation or kept the same from
 * generation to generation.
 * 
 * 
 */
package edu.smcm.ai.genetic;
