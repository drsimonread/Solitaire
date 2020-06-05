package edu.smcm.ai.genetic;

public class Boolean extends Value {
	
	private boolean value;
	
	private static double true_value;
	private static double false_value;
	
	static {
		true_value = 10.0;
		false_value = 0.0;
	}
	
	public static void true_value(double true_value) {
		Boolean.true_value = true_value;
	}
	
	public static void false_value(double false_value) {
		Boolean.false_value = false_value;
	}
	
	public Boolean(boolean value) {
		this.value = value;
	}
	
	public boolean value() {
		return value;
	}
		
	@Override
	public DataType type() {
		return DataType.Boolean;
	}
	
	@Override
	public double toDouble() {
		return value ? true_value : false_value;
	}
	
	@Override
	public String toString() {
		return value ? "TRUE" : "FALSE";
	}
}
