package edu.smcm.ai.examples.solitaire.spider;

import java.util.List;

import edu.smcm.ai.genetic.algorithm.Genotype;
import edu.smcm.games.cards.CardsFaceDownException;
import edu.smcm.games.cards.NotEnoughCardsException;
import edu.smcm.util.ImplementationErrorException;

public class Player {

	private static final int maximum_number_of_moves;

	static {
		maximum_number_of_moves = 300;
	}

	public Move selectBestMove(Game game, List<Move> possible_moves, Genotype genotype) {
		Move best_move;
		double best_score;
		double score;

		best_move = null;
		best_score = 0.0;
		for (Move move : possible_moves) {
			if (null == best_move) {
				best_move = move;
				best_score = genotype.evaluate(game, move);
			} else {
				score = genotype.evaluate(game, move);
				if (score > best_score) {
					best_move = move;
					best_score = score;
				}
			}
		}

		return best_move;
	}

	public Fitness play(Game game, Genotype genotype) {
		boolean won;
		boolean lost;
		int moves;
		List<Move> possible_moves;
		
		moves = 0;
		won = false;
		lost = false;
		while (moves < maximum_number_of_moves && !(won || lost)) {
			possible_moves = game.possibleMoves();
			try {
				game.move(selectBestMove(game, possible_moves, genotype));
			} catch (IllegalMoveException caught) {
				throw new ImplementationErrorException(caught);
			} catch (NotEnoughCardsException caught) {
				throw new ImplementationErrorException(caught);
			} catch (CardsFaceDownException caught) {
				throw new ImplementationErrorException(caught);
			}
			won = game.won();
			lost = game.lost();
			moves = moves + 1;
		}

		return new Fitness(game.scoreSun(), game.scoreTwo(), game.scoreWindows(), moves, game.collected(), won);
	}
}
