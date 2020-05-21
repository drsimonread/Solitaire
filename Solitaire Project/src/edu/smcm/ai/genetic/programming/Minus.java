package edu.smcm.ai.genetic.programming;

import edu.smcm.util.ImplementationErrorException;

public class Minus extends UnaryOperator {

	public Minus(Expression expression) {
		super(expression);
	}

	public OperatorType type() {
		return OperatorType.Arithmetic;
	}
	
	public Value evaluate(Context context) {
		Value result;

		result = expression().evaluate(context);

		switch (result.type()) {
		case Boolean:
			throw new TypeError();
			// break;
		case Integer:
			result = new Integer(-((Integer) result).value());
			break;
		case Double:
			result = new Double(-((Double) result).value());
			break;
		default:
			// TODO Meaningful message
			throw new ImplementationErrorException();
			// break;
		}

		return result;
	}

	@Override
	public String prettyPrint(int level) {
		return prettyPrintHelper("MINUS", level);
	}
}
