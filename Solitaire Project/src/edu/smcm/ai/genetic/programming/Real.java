package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.DataType;

public class Real extends Value {
	
	private double value;
	
	public Real(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}
	
	public void value(double value) {
		this.value = value;
	}
	
	@Override
	public DataType type() {
		return DataType.Real;
	}
	
	public String toString() {
		return "" + value;
	}
}
