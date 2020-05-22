package edu.smcm.ai.genetic.programming;

import java.util.List;
import java.util.Random;

import edu.smcm.util.ImplementationErrorException;

public class GeneratorExample {

	private static Random oracle;

	static {
		oracle = new Random();
	}

	private Expression BinaryArithmeticOperator(Context context) {
		Expression left_expression;
		Expression right_expression;
		Expression result;
		int pick;

		left_expression = factory(true, context);
		right_expression = factory(true, context);

		pick = oracle.nextInt(5);

		switch (pick) {
		case 0:
			result = new Addition(left_expression, right_expression);
			break;
		case 1:
			result = new Division(left_expression, right_expression);
			break;
		case 2:
			result = new Modulus(left_expression, right_expression);
			break;
		case 3:
			result = new Multiply(left_expression, right_expression);
			break;
		case 4:
			result = new Subtraction(left_expression, right_expression);
			break;
		default:
			throw new ImplementationErrorException();
		// break;
		}

		return result;
	}

	private Expression BinaryLogicOperator(Context context) {
		Expression left_expression;
		Expression right_expression;
		Expression result;
		int pick;

		left_expression = factory(false, context);
		right_expression = factory(false, context);

		pick = oracle.nextInt(2);

		switch (pick) {
		case 0:
			result = new And(left_expression, right_expression);
			break;
		case 1:
			result = new Or(left_expression, right_expression);
			break;
		default:
			throw new ImplementationErrorException();
		// break;
		}

		return result;
	}

	private Expression BinaryRelationalOperator(Context context) {
		Expression left_expression;
		Expression right_expression;
		Expression result;
		int pick;

		left_expression = factory(true, context);
		right_expression = factory(true, context);

		pick = oracle.nextInt(2);

		switch (pick) {
		case 0:
			result = new Equals(left_expression, right_expression);
			break;
		case 1:
			result = new GreaterThan(left_expression, right_expression);
			break;
		case 2:
			result = new LessThan(left_expression, right_expression);
			break;
		default:
			throw new ImplementationErrorException();
		// break;
		}

		return result;
	}

	private Expression BinaryOperator(boolean arithmetic, Context context) {
		Expression result;
		int pick;

		if (arithmetic) {
			result = BinaryArithmeticOperator(context);
		} else {
			pick = oracle.nextInt(5);

			switch (pick) {
			case 0:
			case 1:
			case 2:
				result = BinaryLogicOperator(context);
				break;
			case 3:
			case 4:
				result = BinaryRelationalOperator(context);
				break;
			default:
				throw new ImplementationErrorException();
			// break;
			}
		}

		return result;
	}

	public Expression UnaryOperator(boolean arithmetic, Context context) {
		Expression result;
		
		if (arithmetic) {
			result = new Minus(factory(true, context));
		} else {
			result = new Not(factory(false, context));
		}
		
		return result;
	}
	
	/* If statements must result in an arithmetic value */
	private Expression IfStatement(boolean arithmetic, Context context) {
		assert (arithmetic);

		Expression condition;
		Expression true_condition;
		Expression false_condition;

		condition = factory(false, context);
		true_condition = factory(true, context);
		false_condition = factory(true, context);

		return new IfStatement(condition, true_condition, false_condition);
	}

	private Expression Variable(boolean arithmetic, Context context) {
		// TODO Contexts should always have one variable of each type... a random number
		// generator
		Expression result;
		List<Variable> boolean_variables;
		List<Variable> integer_variables;
		List<Variable> real_variables;
		int index;

		boolean_variables = context.booleanVariables();
		integer_variables = context.integerVariables();
		real_variables = context.realVariables();

		if (arithmetic) {
			index = oracle.nextInt(integer_variables.size() + real_variables.size());
			if (index < integer_variables.size()) {
				result = integer_variables.get(index);
			} else {
				// TODO Check for off by one error
				result = real_variables.get(index - integer_variables.size());
			}
		} else {
			result = context.booleanVariables().get(oracle.nextInt(boolean_variables.size()));
		}

		return result;
	}

	/* Constants can only be arithmetic! */
	private Expression Constant(boolean arithmetic, Context context) {
		Expression result;

		assert (arithmetic);

		if (oracle.nextBoolean()) {
			// TODO I don't like the linear distribution here!
			result = new Constant(new Integer(oracle.nextInt()));
		} else {
			// TODO I don't like limitation to [0.0..1.0[
			result = new Constant(new Real(oracle.nextDouble()));
		}

		return result;
	}

	/*
	 * The boolean determines type. If we're expecting a Double or Integer value
	 * it's true, if we're expecting a Boolean type it's false. The context is
	 * necessary to allow us access to variable names.
	 */
	public Expression factory(boolean arithmetic, Context context) {
		Expression result;
		int pick;

		if (arithmetic) {
			pick = oracle.nextInt(10);

			// TODO This has a rather arbitrary probability of any given Expression type
			switch (pick) {
			case 0:
			case 1:
			case 2:
			case 3:
				result = BinaryOperator(arithmetic, context);
				break;
			case 4:
			case 5:
				result = IfStatement(arithmetic, context);
				break;
			case 6:
			case 7:
				result = Constant(arithmetic, context);
			case 8:
			case 9:
				result = Variable(arithmetic, context);
				break;
			default:
				throw new ImplementationErrorException();
			// break;
			}
		} else {
			pick = oracle.nextInt(10);

			// TODO This has a rather arbitrary probability of any given Expression type
			switch (pick) {
			case 0:
			case 1:
			case 2:
			case 3:
				result = BinaryOperator(arithmetic, context);
				break;
			case 4:
			case 5:
				result = UnaryOperator(arithmetic, context);
				break;
			case 6:
			case 7:
			case 8:
			case 9:
				result = Variable(arithmetic, context);
				break;
			default:
				throw new ImplementationErrorException();
			// break;
			}
		}

		return result;
	}
}
