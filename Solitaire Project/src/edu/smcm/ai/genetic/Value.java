/**
 * Values should be immutable!
 * 
 * 
 */
package edu.smcm.ai.genetic;

/**
 *
 */
public abstract class Value {
	public abstract DataType type();
	
	// TODO Does toDouble belong in Value, it seems out of place.
	public abstract double toDouble();
}
