package edu.smcm.ai.genetic.programming;
/**
 * 
 *  Intention is that this should look up a name in a HahTable in Context.  The name will include the DataType.
 */
public abstract class Variable extends Expression {

	private String name;

	public Variable(String name) {
		this.name = name;
	}
		
	public String name() {
		return name;
	}
	
	@Override
	public String prettyPrint(int level) {
		return name;
	}
}
