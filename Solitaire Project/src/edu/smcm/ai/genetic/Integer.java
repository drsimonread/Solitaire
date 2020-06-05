package edu.smcm.ai.genetic;

public class Integer extends Value {
	
	private int value;
	
	public Integer(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	@Override
	public DataType type() {
		return DataType.Integer;
	}
	
	@Override
	public double toDouble() {
		return (double) value;
	}

	@Override
	public String toString() {
		return "" + value;
	}
}
