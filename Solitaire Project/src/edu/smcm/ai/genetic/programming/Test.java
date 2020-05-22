package edu.smcm.ai.genetic.programming;

import java.util.ArrayList;
import java.util.List;

public class Test {

	private static class TestVariable extends Variable {
		
		OperatorType type;
		
		public TestVariable(String name, OperatorType type) {
			super(name);
			this.type = type;
		}
		
		public OperatorType type() {
			return this.type;
		}

		@Override
		public Value evaluate(Context context) {
			// Not going to test evaluation
			return null;
		}
	}
	
	
	private static class TestContext extends Context {
		
		private List<Variable> booleans;
		private List<Variable> integers;
		private List<Variable> reals;
		
		public TestContext() {
			booleans = new ArrayList<Variable>();
			integers = new ArrayList<Variable>();
			reals = new ArrayList<Variable>();
			
			booleans.add(new TestVariable("Bool0", OperatorType.Logic));
			booleans.add(new TestVariable("Bool1", OperatorType.Logic));
			booleans.add(new TestVariable("Bool2", OperatorType.Logic));
			booleans.add(new TestVariable("Bool3", OperatorType.Logic));
			
			integers.add(new TestVariable("Int0", OperatorType.Arithmetic));
			integers.add(new TestVariable("Int1", OperatorType.Arithmetic));
			integers.add(new TestVariable("Int2", OperatorType.Arithmetic));
			integers.add(new TestVariable("Int3", OperatorType.Arithmetic));

			reals.add(new TestVariable("Real0", OperatorType.Arithmetic));
			reals.add(new TestVariable("Real1", OperatorType.Arithmetic));
			reals.add(new TestVariable("Real2", OperatorType.Arithmetic));
			reals.add(new TestVariable("Real3", OperatorType.Arithmetic));
			
		}

		@Override
		public List<Variable> booleanVariables() {
			return booleans;
		}

		@Override
		public List<Variable> integerVariables() {
			return integers;
		}

		@Override
		public List<Variable> realVariables() {
			return reals;
		}
	}
	
	// TODO This should probably be replaced with JUnit though I'm not sure how that would work.
	public static void main(String[] args) {
		GeneratorExample example;
		Context context;
		Expression expression;
		
		example = new GeneratorExample();
		context = new TestContext();
		expression = example.factory(true, 20, context);
				System.out.println(expression.prettyPrint(0));
				
	}
}
