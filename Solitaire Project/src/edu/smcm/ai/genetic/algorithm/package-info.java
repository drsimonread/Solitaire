/**
 * In a Genetic Algorithm a Genotype is simply a list of Genes which evaluate to
 * some Fitness value. The overall fitness is then the sum of the Fitness of
 * each Gene. Each Gene is made up of a weight and a Heuristic. The Heuristic
 * evaluates to a Fitness in a Context. The Fitness of each of the Heuristics
 * are weighted and those weights are subject to evolution.
 * 
 * The weights can all be represented as Doubles and the overall Fitness is a
 * Double. Notice however that the Heuristics might be Real, Integer or Boolean
 * in value.
 * 
 */
package edu.smcm.ai.genetic.algorithm;