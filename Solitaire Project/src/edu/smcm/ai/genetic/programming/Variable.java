package edu.smcm.ai.genetic.programming;
/**
 * 
 *  TODO Intention is that this should look up a name in a HahTable in Context.  The name will include the DataType.
 */
// TODO Should this be abstract?
// TODO Should Variables and Constants have a DataType instead of an OperatorType?
public abstract class Variable extends edu.smcm.ai.genetic.Variable implements PrettyPrintable {
	
	@Override
	public String prettyPrint(int level) {
		return spaces(level) + fullName() + "\n";
	}
}
