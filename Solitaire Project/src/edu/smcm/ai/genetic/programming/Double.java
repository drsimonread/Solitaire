package edu.smcm.ai.genetic.programming;

public class Double extends Value {
	
	private double value;
	
	public Double(double value) {
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
		return DataType.Double;
	}
	
	public String toString() {
		return "" + value;
	}
}
