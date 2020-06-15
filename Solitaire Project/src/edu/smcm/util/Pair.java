package edu.smcm.util;

/**
 * A representation of a pair of values.
 * 
 *  <p> This does its best to mimic the tuple available in other languages such as Python and ML, but requires more explicit handling. 
 *
 *	<p> Pairs are immutable, just because this is a good thing.
 *
 * @param <S> The type of the first element of the Pair.
 * @param <T> The type of the second element of the Pair.
 */
public class Pair<S, T> {
	
	/**
	 * The first element of the pair. 
	 */
	private S first;
	
	/**
	 * The second element of the pair.
	 */
	private T second;
	
	/**
	 * An all arguments constructor.
	 * 
	 * @param first The value of the first element in the pair.
	 * @param second The value of the second element in the pair.
	 */
	public Pair(S first, T second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Accessor for the first element in the pair.
	 * 
	 * @return The value of the first element in the pair.
	 */
	public S first() {
		return first;
	}
	
	/**
	 * Accessor for the second element in the pair.
	 * 
	 * @return The value of the second element in the pair.
	 */
	public T second() {
		return second;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object that) {
		Pair<S, T> t;
		
		t = (Pair<S,T>) that;
		
		return this.first == t.first && this.second == t.second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	
	//TODO Add compareTo to Pair.	
}
