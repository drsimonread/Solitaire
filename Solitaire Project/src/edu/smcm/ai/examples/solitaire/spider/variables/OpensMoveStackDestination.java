package edu.smcm.ai.examples.solitaire.spider.variables;

import edu.smcm.ai.examples.solitaire.spider.Game;
import edu.smcm.ai.examples.solitaire.spider.Move;
import edu.smcm.ai.examples.solitaire.spider.Range;
import edu.smcm.ai.examples.solitaire.spider.algorithm.Position;
import edu.smcm.ai.genetic.Boolean;
import edu.smcm.ai.genetic.DataType;
import edu.smcm.ai.genetic.Value;
import edu.smcm.ai.genetic.Variable;
import edu.smcm.games.cards.Card;
import edu.smcm.games.cards.Suit;

/**
 * The move opens the possibility of moving a stack from another stack.
 * 
 * The stack that it is possible to move can obviously not be the to or from
 * stack. Otherwise we know that a stack can be moved if the newly exposed card
 * is in the moveableRange of the stack being inspected.
 * 
 * Note that the card(s) remaining on the from stack could match the new to
 * stack!
 * 
 * TODO We should probably only count if the stack when moved is bigger than the
 * stack before moving.
 *
 */
public class OpensMoveStackDestination extends Variable {

	// TODO This smells. It's code that's duplicated all over the place.
	/* NOTE: This takes a *position* */
	private Range moveableRange(Game game, int stack, int position) {
		assert (game.cardsInStack(stack) != 0);

		Suit suit;
		int top;

		suit = game.cardAt(stack, position).suit();
		top = 0;
		while (game.cardsInStack(stack) > top && game.cardAt(stack, top).faceUp()
				&& game.cardAt(stack, top).suit() == suit
				&& game.cardAt(stack, top).value() == game.cardAt(stack, position).value() + top) {
			top = top + 1;
		}
		top = top - 1;

		return new Range(game.cardAt(stack, position).value(), game.cardAt(stack, top).value(), suit);
	}

	@Override
	public Value evaluate(edu.smcm.ai.genetic.Position position) {
		Game game;
		Move move;
		Range moved;
		Range moveable;
		Card exposed;

		game = ((Position) position).game();
		move = ((Position) position).move();
		
		if (move.from() == Move.deal_new_row) {
			return new Boolean(false);
		}
		
		moved = moveableRange(game, move.from(), 0);

		if (game.cardsInStack(move.from()) <= moved.size() ||
				!game.cardAt(move.from(), moved.size()).faceUp()) {
			// If we empty the stack that's another Heuristic
			// If it's face down then we can't reason with it
			return new Boolean(false);
		} else {
			exposed = game.cardAt(move.from(), moved.size());

			// WARNING: Loop contains return statements
			// TODO Avoid this!
			for (int stack = 0; stack < Game.number_of_stacks; stack++) {
				if (stack != move.from() && game.cardsInStack(stack) != 0) {
					moveable = moveableRange(game, stack, 0);
					if (stack == move.to()) {
						// Check to see if we've exposed the bottom part of a sequence
						if (moveable.contains(moved.bottom())
								|| moveable.top() + 1 == moved.bottom()) {
							return new Boolean(true);
						}
					} else {
						// Check to see if the stack could move some cards to the exposed card
						if (moveable.contains(exposed.value())
								|| moveable.top() + 1 == exposed.value()) {
							return new Boolean(true);
						}
					}
				}
			}

			return new Boolean(false);
		}
	}

	@Override
	public String abbreviation() {
		return "OMSD";
	}

	@Override
	public String fullName() {
		return "Open Move Stack Destination";
	}

	@Override
	public DataType dataType() {
		return DataType.Boolean;
	}
}
