package edu.smcm.ai.examples.solitaire.spider.variables;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Integer;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.games.cards.Suit;

public class StraightFlush extends Variable {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		Integer result;
		int before_length;
		int after_length;
		Suit suit;
		int base;

		game = ((Position) position).game();
		move = ((Position) position).move();

		if (move.from() == Move.deal_new_row) {
			result = new Integer(0);
		} else {

			// Assume that the cards being moved are a proper moveableSequence
			// The difference in the lengths of the straights will be the difference between
			// the length of the remaining straight and the straight at the from site

			// Compute the length of the straight at the from site
			suit = game.cardAt(move.from(), move.cards() - 1).suit();
			base = game.cardAt(move.from(), move.cards() - 1).value();
			before_length = 0;
			while (game.cardsInStack(move.from()) > before_length + move.cards() && // TODO Off by one?
					game.cardAt(move.from(), before_length + move.cards()).suit() == suit
					&& game.cardAt(move.from(), before_length + move.cards()).value() == base + before_length + 1) {
				before_length = before_length + 1;
			}

			// Compute the length of the straight at the to site
			suit = game.cardAt(move.from(), move.cards() - 1).suit();
			base = game.cardAt(move.from(), move.cards() - 1).value();
			after_length = 0;
			while (after_length < game.cardsInStack(move.to()) && game.cardAt(move.to(), after_length).suit() == suit
					&& game.cardAt(move.to(), after_length).value() == base + after_length + 1) {
				after_length = after_length + 1;
			}

			result = new Integer(after_length - before_length);
		}

		return result;
	}

	@Override
	public String abbreviation() {
		return "SF";
	}

	@Override
	public String fullName() {
		return "Straight Flush";
	}

	@Override
	public DataType dataType() {
		return DataType.Integer;
	}

}
