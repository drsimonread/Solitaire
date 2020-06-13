package edu.smcm.ai.examples.solitaire.spider.algorithm;

import java.util.ArrayList;
import java.util.List;

import edu.smcm.ai.examples.solitaire.spider.variables.CreatedEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.DealNewRow;
import edu.smcm.ai.examples.solitaire.spider.variables.DirtyFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.Discovery;
import edu.smcm.ai.examples.solitaire.spider.variables.FillsEmptyStack;
import edu.smcm.ai.examples.solitaire.spider.variables.NumberOfCards;
import edu.smcm.ai.examples.solitaire.spider.variables.OpensMoveStackDestination;
import edu.smcm.ai.examples.solitaire.spider.variables.StraightFlush;
import edu.smcm.ai.examples.solitaire.spider.variables.TopMoved;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.util.ImplementationErrorException;

public class Factory extends edu.smcm.ai.genetic.Factory {

	private List<Variable> variables;
	private int experiment;
	
	public void experiment(int experiment) {
		this.experiment = experiment;
	}
	
	// TODO Is keeping a list of Variables in the Factory the best design?
	/**
	 * Initialise the Genotype construction with a sequence of Variables.
	 * 
	 * NOTE: The order of adding Variables is important which is why we discriminate
	 * on the experiment a variable incremented for each experiment we perform.
	 *
	 */
	public void initialiseGenotype() {
		variables = new ArrayList<Variable>();

		switch (experiment) {
		case 0:
		case 1:
		case 2:
			variables.add(new CreatedEmptyStack());
			variables.add(new DealNewRow());
			variables.add(new DirtyFlush());
			variables.add(new Discovery());
			variables.add(new FillsEmptyStack());
			variables.add(new NumberOfCards());
			variables.add(new OpensMoveStackDestination());
			variables.add(new StraightFlush());
			variables.add(new TopMoved());
			break;
		default:
			// TODO Meaningful message
			throw new ImplementationErrorException();
		// break;
		}
	}

	@Override
	public Individual makeIndividual() {
		return new Individual();
	}

	@Override
	public Individual makeIndividual(edu.smcm.ai.genetic.Genotype genotype) {
		return new Individual(makeGenotype());
	}

	@Override
	public Genotype makeGenotype() {
		Genotype result;

		result = new Genotype();

		// Create a new Gene for each Variable in order with random weight [0.0..1.0[
		for (Variable variable : variables) {
			result.addGene(variable);
		}

		return result;
	}

	@Override
	public Context makeContext() {
		Context result;
		
		switch (experiment) {
		case 0:
		case 1:
		case 3:
			result = new Context(100, 2, true);
			break;
		default:
			throw new ImplementationErrorException();
			// break;
		}
		
		return result;
	}

}
