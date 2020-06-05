package edu.smcm.ai.genetic;

public class Boolean extends Value {
	
	private boolean value;
	
	public Boolean(boolean value) {
		this.value = value;
	}
	
	
	public boolean value() {
		return value;
	}
	
	public void value(boolean value) {
		this.value = value;
	}
	
	@Override
	public DataType type() {
		return DataType.Boolean;
	}
	
	public String toString() {
		return value ? "TRUE" : "FALSE";
	}
}
