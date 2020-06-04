package edu.smcm.ai.genetic.programming;

import edu.smcm.ai.genetic.DataType;

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
