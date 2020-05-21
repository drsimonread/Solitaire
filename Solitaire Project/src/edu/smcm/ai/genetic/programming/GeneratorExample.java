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

		left_expression = factory(OperatorType.Arithmetic, context);
		right_expression = factory(OperatorType.Arithmetic, context);

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

		left_expression = factory(OperatorType.Logic, context);
		right_expression = factory(OperatorType.Logic, context);

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

		left_expression = factory(OperatorType.Arithmetic, context);
		right_expression = factory(OperatorType.Arithmetic, context);

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

	private Expression BinaryOperator(OperatorType type, Context context) {
		Expression result;

		switch (type) {
		case Arithmetic:
			result = BinaryArithmeticOperator(context);
			break;
		case Logic:
			result = BinaryLogicOperator(context);
			break;
		case Relational:
			result = BinaryRelationalOperator(context);
			break;
		default:
			throw new ImplementationErrorException();
		// break;
		}

		return result;
	}

	private Expression IfStatement(OperatorType type, Context context) {
		assert (type == OperatorType.Arithmetic);
		Expression condition;
		Expression true_condition;
		Expression false_condition;

		condition = factory(OperatorType.Logic, context);
		true_condition = factory(OperatorType.Arithmetic, context);
		false_condition = factory(OperatorType.Arithmetic, context);

		return new IfStatement(condition, true_condition, false_condition);
	}

	private Expression Variable(OperatorType type, Context context) {
		// TODO What happens if we don't have either Arithmetic or Boolean variables?
		// For now we'll assume the Context will provide at least one dummy move
		Expression result;
		List<Variable> boolean_variables;
		List<Variable> integer_variables;
		List<Variable> real_variables;
		int index;

		boolean_variables = context.booleanVariables();
		integer_variables = context.integerVariables();
		real_variables = context.realVariables();

		switch (type) {
		case Arithmetic:
			index = oracle.nextInt(integer_variables.size() + real_variables.size());
			if (index < integer_variables.size()) {
				result = integer_variables.get(index);
			} else {
				result = real_variables.get(index - integer_variables.size() - 1);
			}
			break;
		case Logic:
		case Relational:
			result = context.booleanVariables().get(oracle.nextInt(boolean_variables.size()));
			break;
		default:
			throw new ImplementationErrorException();
			// break;
		}

		return result;
	}

	private Expression Constant(OperatorType type, Context context) {
		Expression result;

		assert (OperatorType.Arithmetic == type);

		if (oracle.nextBoolean()) {
			// TODO I don't like the linear distribution here!
			result = new Constant(new Integer(oracle.nextInt()));
		} else {
			// TODO I don't like limitation to [0.0..1.0[
			result = new Constant(new Real(oracle.nextDouble()));
		}

		return result;
	}

	public Expression factory(OperatorType type, Context context) {
		Expression result;
		int pick;

		pick = oracle.nextInt(10);

		// TODO This has a rather arbitrary probability of any given Expression type
		// Perhaps we should have different switches for Arithmetic and Relational/Logic
		switch (pick) {
		case 0:
		case 1:
		case 2:
		case 3:
			result = BinaryOperator(type, context);
			break;
		case 4:
		case 5:
			if (type == OperatorType.Arithmetic) {
				result = IfStatement(type, context);
			} else if (4 == pick) {
				result = Variable(type, context);
			} else {
				result = Constant(type, context);
			}
			break;
		case 6:
		case 7:
			result = Variable(type, context);
			break;
		case 8:
		case 9:
			if (OperatorType.Arithmetic == type) {
				result = Constant(type, context);
			} else {
				result = BinaryOperator(type, context);
			}
			break;
		default:
			throw new ImplementationErrorException();
		// break;
		}

		return result;
	}
}
