package edu.smcm.ai.genetic;

public class Real extends Value {
	
	private double value;
	
	public Real(double value) {
		this.value = value;
	}
	
	public double value() {
		return value;
	}
	
	@Override
	public DataType type() {
		return DataType.Real;
	}
	
	@Override
	public double toDouble() {
		return value;
	}
	
	@Override
	public String toString() {
		return "" + value;
	}
}
