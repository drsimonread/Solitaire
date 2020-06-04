package edu.smcm.ai.examples.solitaire.spider.heuristics;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Subcontext;
import edu.smcm.ai.genetic.algorithm.Heuristic;
import edu.smcm.ai.genetic.programming.Value;
import edu.smcm.ai.genetic.programming.Integer;
import edu.smcm.games.cards.Suit;

public class StraightFlush extends Heuristic {

	@Override
	public Value evaluate(edu.smcm.ai.genetic.algorithm.Subcontext subcontext) {
		Game game;
		Move move;
		int before_length;
		int after_length;
		Suit suit;
		int base;
		
		game = ((Subcontext) subcontext).game();
		move = ((Subcontext) subcontext).move();
		
		// Assume that the cards being moved are a proper moveableSequence
		// The difference in the lengths of the straights will be the difference between
		// the length of the remaining straight and the straight at the from site

		// Compute the length of the straight at the from site
		suit = game.cardAt(move.from(), move.cards() - 1).suit();
		base = game.cardAt(move.from(), move.cards() - 1).value();
		before_length = 0;
		while (game.cardsInStack(move.from()) > before_length + move.cards() && // TODO Off by one?
				game.cardAt(move.from(), before_length + move.cards()).suit() == suit &&
				game.cardAt(move.from(), before_length + move.cards()).value() == base + before_length + 1) {
			before_length = before_length + 1;
		}
		
		// Compute the length of the straight at the to site
		suit = game.cardAt(move.from(), move.cards() - 1).suit();
		base = game.cardAt(move.from(), move.cards() - 1).value();
		after_length = 0;
		while (after_length < game.cardsInStack(move.to()) &&
				game.cardAt(move.to(), after_length).suit() == suit &&
				game.cardAt(move.to(), after_length).value() == base + after_length + 1) {
			after_length = after_length + 1;
		}

		return new Integer(after_length - before_length);
	}

	@Override
	public String abbreviation() {
		return "SF";
	}

	@Override
	public String fullName() {
		return "Straight Flush";
	}

}
