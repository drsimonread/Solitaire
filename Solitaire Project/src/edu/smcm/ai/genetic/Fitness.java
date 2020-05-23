package edu.smcm.ai.genetic;

public abstract class Fitness implements Comparable<Fitness> {
	public abstract int compareTo(Fitness that);
}
