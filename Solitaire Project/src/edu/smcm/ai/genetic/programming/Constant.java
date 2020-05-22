package edu.smcm.ai.genetic.programming;

public class Constant extends Expression {
	
	private Value value;
	
	public Constant(Value value) {
		this.value = value;
	}
	
	public OperatorType type() {
		return OperatorType.Arithmetic;
	}
	
	@Override
	public Value evaluate(Context context) {
		return value;
	}
	
	public String prettyPrint(int level) {
		return spaces(level) + value + "\n";
	}
	
}
