package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Integer;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;

public class DirtyFlush extends Variable {

	/**
	 * All this code is a copy of Straight Flush with references to Suit removed
	 */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.smcm.ai.examples.solitaire.spider.Heuristic#value(edu.smcm.ai.examples.
	 * solitaire.spider.MoveStack)
	 */
	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		int before_length;
		int after_length;
		int base;
		
		game = ((Position) position).game();
		move = ((Position) position).move();

		// Assume that the cards being moved are a proper moveableSequence
		// The difference in the lengths of the straights will be the difference between
		// the length of the remaining straight and the straight at the from site

		// Compute the length of the straight at the from site
		base = game.cardAt(move.from(), move.cards() - 1).value();
		before_length = 0;
		while (game.cardsInStack(move.from()) > before_length + move.cards() && // TODO Off by one?
				game.cardAt(move.from(), before_length + move.cards()).value() == base + before_length + 1) {
			before_length = before_length + 1;
		}
		
		// Compute the length of the straight at the to site
		base = game.cardAt(move.from(), move.cards() - 1).value();
		after_length = 0;
		while (after_length < game.cardsInStack(move.to()) &&
				game.cardAt(move.to(), after_length).value() == base + after_length + 1) {
			after_length = after_length + 1;
		}

		return new Integer(after_length - before_length);
	}

	@Override
	public String abbreviation() {
		return "DF";
	}

	@Override
	public String fullName() {
		return "Dirty Flush";
	}

	@Override
	public DataType dataType() {
		return DataType.Integer;
	}

}
