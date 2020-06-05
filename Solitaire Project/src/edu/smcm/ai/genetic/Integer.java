package edu.smcm.ai.genetic;

public class Integer extends Value {
	
	private int value;
	
	public Integer(int value) {
		this.value = value;
	}
	
	public int value() {
		return value;
	}
	
	public void value(int value) {
		this.value = value;
	}
	
	@Override
	public DataType type() {
		return DataType.Integer;
	}

	public String toString() {
		return "" + value;
	}
}
